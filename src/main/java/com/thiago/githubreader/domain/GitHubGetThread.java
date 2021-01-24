package com.thiago.githubreader.domain;

import com.thiago.githubreader.domain.githubrepo.GitHubFile;
import com.thiago.githubreader.domain.githubrepo.GitHubRepoFileContainer;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.concurrent.ExecutorService;

public class GitHubGetThread implements Runnable {
    private final @NotBlank String url;
    private final @NotNull GitHubRepoConnectionHandler gitHubRepoConnectionHandler;
    private final @NotNull GitHubRepoFileContainer gitHubRepoFileContainer;
    private final @NotNull ExecutorService executorService;
    private final @NotNull GitHubFileGetterExecutionController gitHubFileGetterExecutionController;

    public GitHubGetThread(@NotBlank String url,
                           @NotNull GitHubRepoConnectionHandler gitHubRepoConnectionHandler,
                           @NotNull GitHubRepoFileContainer gitHubRepoFileContainer,
                           @NotNull ExecutorService executorService,
                           @NotNull GitHubFileGetterExecutionController gitHubFileGetterExecutionController) {
        this.url = url;
        this.gitHubRepoConnectionHandler = gitHubRepoConnectionHandler;
        this.gitHubRepoFileContainer = gitHubRepoFileContainer;
        this.executorService = executorService;
        this.gitHubFileGetterExecutionController = gitHubFileGetterExecutionController;

    }

    @Override
    public void run() {
        // TODO handle exceptions
        // If it's a file, creates file and adds to hashmap
        String html = gitHubRepoConnectionHandler.httpGetRequestToString(this.url);
        if (html != "") {
            if (isPageFile(html)) {
                // If file is not already inserted, inserts
                GitHubFile file = new GitHubFile(html);
                if (this.gitHubFileGetterExecutionController
                        .checkIfAvailableAndInsertIfSoFile(this.url));
                    gitHubRepoFileContainer.addFile(file);
            } else {
                // If it's a dir, creates new thread for every file/dir inside the current dir
                List<String> urls = GitHubRepoDirParser.getDirOrFileUrls(html);

                // Creates a new thread for every dir/file, checks for dirs already searched
                urls.stream()
                        .filter(u -> this.gitHubFileGetterExecutionController.checkIfAvailableAndInsertIfSoDir(u))
                        .forEach(u -> this.executorService.execute(new GitHubGetThread(
                                        u,
                                        this.gitHubRepoConnectionHandler,
                                        this.gitHubRepoFileContainer,
                                        this.executorService,
                                        this.gitHubFileGetterExecutionController)
                                )
                        );
            }
        }
    }

    /**
     * Determines if an html represents a github file
     *
     * @param html input html
     * @return true if page is a github file, else false
     */
    private boolean isPageFile(String html) {
        return html.contains("octicon-trashcan");
    }

}
