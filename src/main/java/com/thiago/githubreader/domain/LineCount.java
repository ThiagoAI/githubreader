package com.thiago.githubreader.domain;

public final class LineCount {
    private final long numberOfLines;

    public LineCount(long numberOfLines) {
        this.numberOfLines = numberOfLines;
    }

    public long getNumberOfLines() {
        return numberOfLines;
    }
}
