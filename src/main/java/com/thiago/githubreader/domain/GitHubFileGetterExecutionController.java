package com.thiago.githubreader.domain;

import javax.validation.constraints.NotNull;
import java.util.concurrent.ConcurrentHashMap;

public class GitHubFileGetterExecutionController {
    private final ConcurrentHashMap<String, String> insertedFiles;
    private final ConcurrentHashMap<String, String> insertedDirs;

    public GitHubFileGetterExecutionController() {
        this.insertedFiles = new ConcurrentHashMap<>();
        this.insertedDirs = new ConcurrentHashMap<>();
    }

    public boolean checkIfAvailableAndInsertIfSoFile(@NotNull String fileName) {
        if (!insertedFiles.contains(fileName)) {
            insertedFiles.put(fileName, fileName);
            return true;
        }
        return false;
    }

    public boolean checkIfAvailableAndInsertIfSoDir(@NotNull String dirName) {
        if (!insertedDirs.contains(dirName)) {
            insertedDirs.put(dirName, dirName);
            return true;
        }
        return false;
    }
}
