package com.thiago.githubreader.domain;

import com.thiago.githubreader.domain.githubrepo.GitHubRepo;

import javax.validation.constraints.NotNull;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class GitHubRepoFileGetter {
    // Populates repo with files from repo url
    public static void GetFilesInfoFromRepo(@NotNull GitHubRepo gitHubRepo,
                                            @NotNull GitHubRepoConnectionHandler gitHubRepoConnectionHandler) {
        ThreadPoolExecutor es = new ThreadPoolExecutor(8, 6000, 10,
                TimeUnit.SECONDS, new ArrayBlockingQueue<>(5992));

        GitHubFileGetterExecutionController gitHubFileGetterExecutionController =
                new GitHubFileGetterExecutionController();
        es.execute(new GitHubGetThread(gitHubRepo.getUrl(),
                gitHubRepoConnectionHandler,
                gitHubRepo.getGitHubRepoFileContainer(),
                es,
                gitHubFileGetterExecutionController));
        while (es.getActiveCount() != 0) {
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        es.shutdown();
        try {
            es.awaitTermination(60, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
