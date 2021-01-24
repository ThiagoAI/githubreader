package com.thiago.githubreader.domain.githubrepo;

import com.thiago.githubreader.domain.BytesFormatter;

import java.util.ArrayList;
import java.util.Map;

public class GitHubRepoFileContainer {
    private long totalLines;
    private double totalBytes;
    private final Map<String, GitHubRepoFileExtensionList> map;

    public GitHubRepoFileContainer(Map<String, GitHubRepoFileExtensionList> map) {
        this.map = map;
        this.totalBytes = 0;
        this.totalLines = 0;
    }

    public void addFile(GitHubFile file) {
        // If there is no list yet, creates one
        if (this.map.get(file.getFileExtension()) == null) {
            this.map.put(file.getFileExtension(),
                    new GitHubRepoFileExtensionList(
                            file.getFileExtension(),
                            new ArrayList<>())
            );
        }
        this.map.get(file.getFileExtension()).addFile(file);
        // TODO check for async issues with total bytes and line
        this.totalBytes += file.getBytesSize().getBytes();
        this.totalLines += file.getLineCount().getNumberOfLines();
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

    public Map<String, GitHubRepoFileExtensionList> getMap() {
        return map;
    }

    public long fileCount() {
        return this.map.values().stream()
                .map(i -> i.fileCount()).reduce(0L, (i, e) -> i + e);
    }
}
