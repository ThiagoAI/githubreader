package com.thiago.githubreader.adapters.web.controllers;

import com.thiago.githubreader.adapters.web.api.ReadrepoApi;
import com.thiago.githubreader.adapters.web.mappers.GitHubRepoApiMapper;
import com.thiago.githubreader.adapters.web.model.ReadRepoRequestApiModel;
import com.thiago.githubreader.adapters.web.model.ReadRepoResponseApiModel;
import com.thiago.githubreader.application.ports.in.ReadGitHubRepoUseCase;
import lombok.RequiredArgsConstructor;
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
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.concurrent.ExecutionException;

@RestController
@RequiredArgsConstructor
public class ReadrepoApiImpl implements ReadrepoApi {
    private final ReadGitHubRepoUseCase readGitHubRepoUseCase;

    @Override
    public ResponseEntity<ReadRepoResponseApiModel> readrepo(@Valid ReadRepoRequestApiModel readRepoRequestApiModel) {
//        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
//        // Increase max total connection to 200
//        cm.setMaxTotal(200);
//        // Increase default max connection per route to 20
//        cm.setDefaultMaxPerRoute(20);
//        // Increase max connections for localhost:80 to 50
//        HttpHost localhost = new HttpHost(readRepoRequestApiModel.getUrl(), 80);
//        cm.setMaxPerRoute(new HttpRoute(localhost), 50);
//
//        CloseableHttpClient httpClient = HttpClients.custom()
//                .setConnectionManager(cm)
//                .build();
//        HttpGet httpget = new HttpGet(readRepoRequestApiModel.getUrl());
//        HttpClientContext context = HttpClientContext.create();
//
//        String x = "532 MB";
//        try {
//            CloseableHttpResponse response = httpClient.execute(
//                    httpget, context);
//            try {
//                HttpEntity entity = response.getEntity();
//                x = EntityUtils.toString(entity);
//            } finally {
//                response.close();
//            }
//            httpget = new HttpGet("https://github.com/ThiagoAI/PG-Dijkstra/blob/master/edge.c");
//            response = httpClient.execute(
//                    httpget, context);
//            try {
//                HttpEntity entity = response.getEntity();
//                x = EntityUtils.toString(entity);
//            } finally {
//                response.close();
//            }
//        } catch (ClientProtocolException ex) {
//            // Handle protocol errors
//            return ResponseEntity.ok(
//                    new ReadRepoResponseApiModel()
//                            .totalLines(BigDecimal.valueOf(2245))
//                            .totalSize("1 MB")
//                            .fileGroups(new LinkedList<>())
//            );
//        } catch (IOException ex) {
//            // Handle I/O errors
//            return ResponseEntity.ok(
//                    new ReadRepoResponseApiModel()
//                            .totalLines(BigDecimal.valueOf(2245))
//                            .totalSize("2 MB")
//                            .fileGroups(new LinkedList<>())
//            );
//        }

//        return ResponseEntity.ok(
//                new ReadRepoResponseApiModel()
//                        .totalLines(BigDecimal.valueOf(2245))
//                        .totalSize(x)
//                        .fileGroups(new LinkedList<>())
//        );

        try {
            return ResponseEntity.ok(
                    GitHubRepoApiMapper.toGitHubRepoModel(
                            readGitHubRepoUseCase.readGitHubRepo(
                                    readRepoRequestApiModel.getUrl()).get())
            );
        } catch (InterruptedException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } catch (ExecutionException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
