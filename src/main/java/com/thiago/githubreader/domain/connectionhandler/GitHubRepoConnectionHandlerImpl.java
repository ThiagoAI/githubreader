package com.thiago.githubreader.domain.connectionhandler;

import com.revinate.guava.util.concurrent.RateLimiter;
import com.thiago.githubreader.application.exceptions.GetScrapeRequestFailed;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
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
import javax.validation.constraints.NotNull;
import java.io.IOException;

public class GitHubRepoConnectionHandlerImpl implements GitHubRepoConnectionHandler {
    private final @NotNull HttpClientConnectionManager connectionManager;
    private CloseableHttpClient httpClients;
    final RateLimiter rateLimiter;

    public GitHubRepoConnectionHandlerImpl(
            @NotNull HttpClientConnectionManager connectionManager,
            double masRequestsPerSecond) {
        this.connectionManager = connectionManager;
        this.rateLimiter = RateLimiter.create(masRequestsPerSecond);
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
    public String httpGetRequestToString(@NotBlank String url) throws GetScrapeRequestFailed {
        rateLimiter.acquire();
        HttpGet httpget = new HttpGet(url);
        HttpClientContext context = HttpClientContext.create();
        try {
            CloseableHttpResponse response = httpClients.execute(
                    httpget, context);
            try {
                if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK)
                    throw new GetScrapeRequestFailed(url, response.getStatusLine().getStatusCode());
                HttpEntity entity = response.getEntity();
                return EntityUtils.toString(entity);
            } finally {
                response.close();
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
            throw new GetScrapeRequestFailed(url, e);
        } catch (IOException e) {
            e.printStackTrace();
            throw new GetScrapeRequestFailed(url, e);
        }
    }

    @Override
    public void closeConnection() {
        if (this.httpClients != null) {
            try {
                httpClients.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
