package com.thiago.githubreader.domain;

import org.apache.http.impl.client.CloseableHttpClient;
import org.springframework.scheduling.annotation.Async;

import javax.validation.constraints.NotBlank;

public interface ConnectionHandler {
    void openConnection(@NotBlank String url);
    String httpGetRequestToString(@NotBlank String url);
    void closeConnection();
}
