package com.thiago.githubreader.domain;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

public class GitHubGetThread implements Runnable {
    private final @NotBlank String url;
    private final @NotNull GitHubRepoConnectionHandler gitHubRepoConnectionHandler;
    private final @NotNull GitHubRepoFileContainer gitHubRepoFileContainer;
    private final @NotNull ExecutorService executorService;

    public GitHubGetThread(@NotBlank String url,
                           @NotNull GitHubRepoConnectionHandler gitHubRepoConnectionHandler,
                           @NotNull GitHubRepoFileContainer gitHubRepoFileContainer,
                           @NotNull ExecutorService executorService) {
        this.url = url;
        this.gitHubRepoConnectionHandler = gitHubRepoConnectionHandler;
        this.gitHubRepoFileContainer = gitHubRepoFileContainer;
        this.executorService = executorService;
    }

    @Override
    public void run() {
        System.out.println("LUL" + this.url + " AYY");
        // TODO handle exceptions
        // If it's a file, creates file and adds to hashmap
        String html = gitHubRepoConnectionHandler.httpGetRequestToString(this.url);
        if (html != "") {
            if (isPageFile(html)) {
                System.out.println("File " + this.url);
                gitHubRepoFileContainer.addFile(
                        new GitHubFile(
                                html
                        )
                );
            }
            else {
                // If it's a dir, creates new thread for every file/dir inside the current dir
                System.out.println("Dir " + this.url);
                List<String> urls = GitHubRepoDirParser.getDirOrFileUrls(html);
                List<Future> futures = urls.stream().map(u -> this.executorService.submit(new GitHubGetThread(
                                u,
                                this.gitHubRepoConnectionHandler,
                                this.gitHubRepoFileContainer,
                                this.executorService)
                        )
                ).collect(Collectors.toList());
                while (isAnyFutureNotDone(futures)) {
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        } else System.out.println("ERROR: " + this.url);
        System.out.println("FINISHED " + this.url);
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

    private boolean isAnyFutureNotDone(List<Future> futures) {
        boolean result = false;
        for (Future f: futures) {
            if (!f.isDone()) {
                result = true;
                break;
            }
        }
        return result;
    }

}
