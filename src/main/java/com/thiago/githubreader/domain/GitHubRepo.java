package com.thiago.githubreader.domain;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GitHubRepo {
    private final GitHubRepoFileContainer gitHubRepoFileContainer;
    private final @NotBlank String url;

    public GitHubRepo(@NotBlank String url, @NotNull GitHubRepoConnectionHandler connectionHandler) {
        this.url = url;
        this.gitHubRepoFileContainer = new GitHubRepoFileContainer(new ConcurrentHashMap<>());
        GitHubRepoFileGetter.GetFilesInfoFromRepo(this, connectionHandler);
    }

    public String getUrl() {
        return url;
    }

    public GitHubRepoFileContainer getGitHubRepoFileContainer() {
        return gitHubRepoFileContainer;
    }

    public double getTotalBytes() {
        return this.gitHubRepoFileContainer.getTotalBytes();
    }

    public long getTotalLines() {
        return this.gitHubRepoFileContainer.getTotalLines();
    }
}
