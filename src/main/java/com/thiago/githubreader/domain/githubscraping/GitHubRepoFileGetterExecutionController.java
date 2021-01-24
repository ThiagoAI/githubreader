package com.thiago.githubreader.domain.githubscraping;

import com.thiago.githubreader.domain.githubrepo.GitHubRepo;
import com.thiago.githubreader.domain.githubrepo.GitHubRepoFile;

import javax.validation.constraints.NotNull;
import java.util.concurrent.ConcurrentHashMap;

public class GitHubRepoFileGetterExecutionController {
    private final ConcurrentHashMap<String, String> insertedFiles;
    private final ConcurrentHashMap<String, String> insertedDirs;
    private final @NotNull GitHubRepo gitHubRepo;

    public GitHubRepoFileGetterExecutionController(
            @NotNull GitHubRepo gitHubRepo) {
        this.insertedFiles = new ConcurrentHashMap<>();
        this.insertedDirs = new ConcurrentHashMap<>();
        this.gitHubRepo = gitHubRepo;
    }

    public boolean checkIfAvailableAndInsertIfSoFile(@NotNull String fileName, @NotNull GitHubRepoFile file) {
        if (!insertedFiles.contains(fileName)) {
            insertedFiles.put(fileName, fileName);
            gitHubRepo.addRepoFile(file);
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
