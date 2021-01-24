package com.thiago.githubreader.application.exceptions;

public class FailedToGetGitHubFilesException extends RuntimeException {
    public FailedToGetGitHubFilesException(String gitHubRepoUrl) {
        super("An unexpected error ocurred while scraping: \"" + gitHubRepoUrl + "\".");
    }
}
