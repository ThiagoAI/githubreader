package com.thiago.githubreader.domain.githubrepo;

import com.thiago.githubreader.domain.connectionhandler.GitHubRepoConnectionHandler;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class GitHubRepo {
    private final GitHubRepoFileContainer gitHubRepoFileContainer;
    private final @NotBlank String url;

    public GitHubRepo(@NotBlank String url, @NotNull GitHubRepoConnectionHandler connectionHandler) {
        this.url = url;
        this.gitHubRepoFileContainer = new GitHubRepoFileContainer(new ConcurrentHashMap<>());
    }

    public String getUrl() {
        return url;
    }

    public double getTotalBytes() {
        return this.gitHubRepoFileContainer.getTotalBytes();
    }

    public List<GitHubRepoFileExtensionList> getCopyListOfFileExtensionGroups() {
        return this.gitHubRepoFileContainer.getCopyOfAllFileExtensionGroups();
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

    public void addRepoFile(GitHubRepoFile file) {
        this.gitHubRepoFileContainer.addFile(file);
    }
}
