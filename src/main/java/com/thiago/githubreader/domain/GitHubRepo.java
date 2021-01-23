package com.thiago.githubreader.domain;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.concurrent.ConcurrentHashMap;

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

    public String getTotalBytesFormattedString() {
        return this.gitHubRepoFileContainer.getTotalBytesFormattedString();
    }

    public long getTotalLines() {
        return this.gitHubRepoFileContainer.getTotalLines();
    }

    public long getFileCount() {
        return this.gitHubRepoFileContainer.fileCount();
    }
}
