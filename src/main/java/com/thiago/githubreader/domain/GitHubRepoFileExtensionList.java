package com.thiago.githubreader.domain;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class GitHubRepoFileExtensionList {
    private long totalLines;
    private double totalBytes;
    private final @NotNull String fileExtension;
    private final @NotNull List<GitHubFile> list;

    public GitHubRepoFileExtensionList(@NotNull String fileExtension,
                                       @NotNull List<GitHubFile> list) {
        this.fileExtension = fileExtension;
        this.list = list;
        this.totalLines = 0;
        this.totalBytes = 0;
    }

    public void addFile(@NotNull GitHubFile gitHubFile) {
        this.list.add(gitHubFile);
        this.totalBytes += gitHubFile.getBytesSize().getBytes();
        this.totalLines += gitHubFile.getLineCount().getNumberOfLines();
    }

    public long getTotalLines() {
        return totalLines;
    }

    public double getTotalBytes() {
        return totalBytes;
    }

    public String getFileExtension() {
        return fileExtension;
    }

    public List<GitHubFile> getList() {
        return list;
    }
}
