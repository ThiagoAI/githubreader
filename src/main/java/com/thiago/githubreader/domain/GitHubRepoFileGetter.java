package com.thiago.githubreader.domain;

import javax.validation.constraints.NotNull;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class GitHubRepoFileGetter {
    // Populates repo with files from repo url
    public static void GetFilesInfoFromRepo(@NotNull GitHubRepo gitHubRepo,
    @NotNull GitHubRepoConnectionHandler gitHubRepoConnectionHandler) {
        ExecutorService es = Executors.newCachedThreadPool();
        Future future = es.submit(new GitHubGetThread(gitHubRepo.getUrl(),
                gitHubRepoConnectionHandler,
                gitHubRepo.getGitHubRepoFileContainer(),
                es));
        while (!future.isDone()) {
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        es.shutdown();
        try {
            es.awaitTermination(60,TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
