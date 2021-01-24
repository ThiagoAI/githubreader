package com.thiago.githubreader.domain.connectionhandler;

import javax.validation.constraints.NotBlank;

public interface GitHubRepoConnectionHandler {
    void openConnection();

    String httpGetRequestToString(@NotBlank String url);

    void closeConnection();
}
