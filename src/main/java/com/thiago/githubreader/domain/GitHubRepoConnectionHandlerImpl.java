package com.thiago.githubreader.domain;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

import javax.validation.constraints.NotBlank;
import java.io.IOException;

public class GitHubRepoConnectionHandlerImpl implements GitHubRepoConnectionHandler {
    private final HttpClientConnectionManager connectionManager;
    private CloseableHttpClient httpClients;

    public GitHubRepoConnectionHandlerImpl(HttpClientConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    @Override
    public void openConnection(@NotBlank String url) {
        httpClients = HttpClients.custom()
                .setConnectionManager(this.connectionManager)
                .build();
    }

    @Override
    public String httpGetRequestToString(@NotBlank String url) {
        HttpGet httpget = new HttpGet(url);
        HttpClientContext context = HttpClientContext.create();
        try {
            CloseableHttpResponse response = httpClients.execute(
                    httpget, context);
            try {
                HttpEntity entity = response.getEntity();
                return EntityUtils.toString(entity);
            } finally {
                response.close();
            }
        } catch (ClientProtocolException e) {
            // TODO Handle protocol errors
            e.printStackTrace();
            return "";
        } catch (IOException e) {
            // TODO Handle I/O errors
            e.printStackTrace();
            return "";
        }
    }

    @Override
    public void closeConnection() {
        if (this.httpClients != null) {
            try {
                httpClients.close();
            } catch (IOException e) {
                // TODO handle I/O errors
                e.printStackTrace();
            }
        }
    }
}
