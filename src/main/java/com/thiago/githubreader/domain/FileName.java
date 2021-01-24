package com.thiago.githubreader.domain;

import javax.validation.constraints.NotNull;

public final class FileName {
    private final @NotNull String fileName;
    private final @NotNull String fileExtension;

    public FileName(@NotNull String fileName, @NotNull String fileExtension) {
        this.fileName = fileName;
        this.fileExtension = fileExtension;
    }

    public String getFileName() {
        return fileName;
    }

    public String getFileExtension() {
        return fileExtension;
    }
}
