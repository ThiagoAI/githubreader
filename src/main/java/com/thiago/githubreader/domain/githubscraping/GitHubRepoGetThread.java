package com.thiago.githubreader.domain.githubscraping;

import com.thiago.githubreader.domain.connectionhandler.GitHubRepoConnectionHandler;
import com.thiago.githubreader.domain.githubrepo.GitHubRepoFile;
import com.thiago.githubreader.domain.githubscraping.parsers.GitHubRepoDirParser;
import com.thiago.githubreader.domain.githubscraping.parsers.GitHubRepoFileParser;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.concurrent.ExecutorService;

public class GitHubRepoGetThread implements Runnable {
    private final @NotBlank String url;
    private final @NotNull GitHubRepoConnectionHandler gitHubRepoConnectionHandler;
    private final @NotNull ExecutorService executorService;
    private final @NotNull GitHubRepoFileGetterExecutionController gitHubRepoFileGetterExecutionController;

    public GitHubRepoGetThread(@NotBlank String url,
                               @NotNull GitHubRepoConnectionHandler gitHubRepoConnectionHandler,
                               @NotNull ExecutorService executorService,
                               @NotNull GitHubRepoFileGetterExecutionController gitHubRepoFileGetterExecutionController) {
        this.url = url;
        this.gitHubRepoConnectionHandler = gitHubRepoConnectionHandler;
        this.executorService = executorService;
        this.gitHubRepoFileGetterExecutionController = gitHubRepoFileGetterExecutionController;

    }

    @Override
    public void run() {
        String html = gitHubRepoConnectionHandler.httpGetRequestToString(this.url);
        if (!html.equals("")) {
            // If it's a file, creates file
            if (GitHubRepoFileParser.isPageFile(html)) {
                // If file is not already inserted, inserts
                GitHubRepoFile file = GitHubRepoFileParser.parseToFile(html);
                this.gitHubRepoFileGetterExecutionController
                        .checkIfAvailableAndInsertIfSoFile(this.url, file);
            } else {
                // If it's a dir, creates new thread for every file/dir inside the current dir
                List<String> urls = GitHubRepoDirParser.getDirOrFileUrls(html);

                // Creates a new thread for every dir/file, checks for dirs already searched
                urls.stream()
                        .filter(u -> this.gitHubRepoFileGetterExecutionController.checkIfAvailableAndInsertIfSoDir(u))
                        .forEach(u -> this.executorService.execute(new GitHubRepoGetThread(
                                        u,
                                        this.gitHubRepoConnectionHandler,
                                        this.executorService,
                                        this.gitHubRepoFileGetterExecutionController)
                                )
                        );
            }
        }
    }
}
