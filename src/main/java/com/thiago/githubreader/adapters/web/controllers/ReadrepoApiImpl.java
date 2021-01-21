package com.thiago.githubreader.adapters.web.controllers;

import com.thiago.githubreader.adapters.web.api.ReadrepoApi;
import com.thiago.githubreader.adapters.web.model.ReadRepoResponseApiModel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.LinkedList;

@RestController
@RequiredArgsConstructor
public class ReadrepoApiImpl implements ReadrepoApi {
    @Override
    public ResponseEntity<ReadRepoResponseApiModel> readrepo(String repoURL) {
        return ResponseEntity.ok(
                new ReadRepoResponseApiModel()
                        .totalLines(BigDecimal.valueOf(2245))
                        .totalSize("532 MB")
                        .fileGroups(new LinkedList<>())
        );
    }
}
