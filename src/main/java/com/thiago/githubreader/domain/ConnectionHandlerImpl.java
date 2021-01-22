package com.thiago.githubreader.domain;

import com.thiago.githubreader.adapters.web.model.ReadRepoResponseApiModel;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.springframework.http.ResponseEntity;

import javax.validation.constraints.NotBlank;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.LinkedList;

public class ConnectionHandlerImpl implements ConnectionHandler {
    private PoolingHttpClientConnectionManager connectionManager;
    private CloseableHttpClient httpClients;

    @Override
    public void openConnection(@NotBlank String url) {
        connectionManager = new PoolingHttpClientConnectionManager();
        connectionManager.setMaxTotal(2000);
        connectionManager.setDefaultMaxPerRoute(100);
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
        } catch (ClientProtocolException ex) {
            // TODO Handle protocol errors
            return "";
        } catch (IOException ex) {
            // TODO Handle I/O errors
            return "";
        }
    }

    @Override
    public void closeConnection() {
        if (this.connectionManager != null)
            connectionManager.close();

    }
}
