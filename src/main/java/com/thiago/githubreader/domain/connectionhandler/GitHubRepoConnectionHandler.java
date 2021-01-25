package com.thiago.githubreader.domain.connectionhandler;

import com.thiago.githubreader.application.exceptions.GetScrapeRequestFailed;

import javax.validation.constraints.NotBlank;

public interface GitHubRepoConnectionHandler {
    void openConnection();

    String httpGetRequestToString(@NotBlank String url) throws GetScrapeRequestFailed;

    void closeConnection();
}
