package com.thiago.githubreader.domain.githubscraping;

import com.thiago.githubreader.application.exceptions.FailedToGetGitHubFilesException;
import com.thiago.githubreader.domain.connectionhandler.GitHubRepoConnectionHandler;
import com.thiago.githubreader.domain.githubrepo.GitHubRepo;

import javax.validation.constraints.NotNull;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class GitHubRepoFileGetter {
    /**
     * Populates githubrepo with files
     *
     * @param gitHubRepo                  target repo
     * @param gitHubRepoConnectionHandler connection handler for necessary requests
     */
    public static void GetFilesInfoFromRepo(@NotNull GitHubRepo gitHubRepo,
                                            @NotNull GitHubRepoConnectionHandler gitHubRepoConnectionHandler)
            throws FailedToGetGitHubFilesException {
        // Create thread pool
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(8, 6000, 10,
                TimeUnit.SECONDS, new ArrayBlockingQueue<>(5992));

        // Executes starting thread
        GitHubRepoFileGetterExecutionController executionController =
                new GitHubRepoFileGetterExecutionController(gitHubRepo);
        poolExecutor.execute(
                new GitHubRepoGetThread(
                        gitHubRepo.getUrl(),
                        gitHubRepoConnectionHandler,
                        poolExecutor,
                        executionController
                )
        );

        // Wait for no active threads or an exception
        while (poolExecutor.getActiveCount() != 0 && executionController.exceptionCount() == 0) {
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Shutdown pool executor
        poolExecutor.shutdown();
        try {
            poolExecutor.awaitTermination(60, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (executionController.exceptionCount() > 0)
            throw new FailedToGetGitHubFilesException(gitHubRepo.getUrl());
    }
}
