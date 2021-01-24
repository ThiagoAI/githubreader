package com.thiago.githubreader.domain.githubrepo;

import com.thiago.githubreader.domain.bytesutils.BytesFormatter;

import javax.validation.constraints.NotNull;
import java.util.List;

public class GitHubRepoFileExtensionList {
    private long totalLines;
    private double totalBytes;
    private final @NotNull String fileExtension;
    private final @NotNull List<GitHubRepoFile> list;

    public GitHubRepoFileExtensionList(@NotNull String fileExtension,
                                       @NotNull List<GitHubRepoFile> list) {
        this.fileExtension = fileExtension;
        this.list = list;
        this.totalLines = 0;
        this.totalBytes = 0;
    }

    public void addFile(@NotNull GitHubRepoFile gitHubRepoFile) {
        this.list.add(gitHubRepoFile);
        this.totalBytes += gitHubRepoFile.getBytesSize().getBytes();
        this.totalLines += gitHubRepoFile.getLineCount().getNumberOfLines();
    }

    public long getTotalLines() {
        return totalLines;
    }

    public double getTotalBytes() {
        return totalBytes;
    }

    public String getTotalBytesFormattedString() {
        return BytesFormatter.formatBytes(this.totalBytes);
    }

    public String getFileExtension() {
        return fileExtension;
    }

    public List<GitHubRepoFile> getCopyOfAllFiles() {
        return List.copyOf(this.list);
    }

    public long fileCount() {
        return this.list.stream().count();
    }
}
