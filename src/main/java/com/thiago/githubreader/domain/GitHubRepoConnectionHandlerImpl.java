package com.thiago.githubreader.domain;

import com.revinate.guava.util.concurrent.RateLimiter;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import javax.validation.constraints.NotBlank;
import java.io.IOException;

public class GitHubRepoConnectionHandlerImpl implements GitHubRepoConnectionHandler {
    private final HttpClientConnectionManager connectionManager;
    private CloseableHttpClient httpClients;
    final RateLimiter rateLimiter;

    public GitHubRepoConnectionHandlerImpl(HttpClientConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
        this.rateLimiter = RateLimiter.create(2.0);
    }

    @Override
    public void openConnection() {
        httpClients = HttpClients.custom()
                .setConnectionManager(this.connectionManager)
                .setConnectionManagerShared(true)
                .setMaxConnTotal(4)
                .setKeepAliveStrategy(DefaultConnectionKeepAliveStrategy.INSTANCE)
                .build();
    }

    @Override
    public String httpGetRequestToString(@NotBlank String url) {
        rateLimiter.acquire();
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
