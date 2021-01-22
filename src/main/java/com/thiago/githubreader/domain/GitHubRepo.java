package com.thiago.githubreader.domain;

import java.util.ArrayList;

public class GitHubRepo {
    private String totalByteSize;
    private long totalLineCount;

    public GitHubRepo(String totalByteSize, long totalLineCount) {
        this.totalByteSize = totalByteSize;
        this.totalLineCount = totalLineCount;
    }

    public long getTotalLineCount() {
        return totalLineCount;
    }

    public String getTotalByteSize() {
        return totalByteSize;
    }
}
