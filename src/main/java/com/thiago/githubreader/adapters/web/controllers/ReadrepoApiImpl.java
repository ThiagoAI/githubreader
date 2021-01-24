package com.thiago.githubreader.adapters.web.controllers;

import com.thiago.githubreader.adapters.web.api.ReadrepoApi;
import com.thiago.githubreader.adapters.web.mappers.GitHubRepoApiMapper;
import com.thiago.githubreader.adapters.web.model.ReadRepoRequestApiModel;
import com.thiago.githubreader.adapters.web.model.ReadRepoResponseApiModel;
import com.thiago.githubreader.application.ports.in.ReadGitHubRepoUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URISyntaxException;
import java.util.concurrent.ExecutionException;

@RestController
@RequiredArgsConstructor
public class ReadrepoApiImpl implements ReadrepoApi {
    private final ReadGitHubRepoUseCase readGitHubRepoUseCase;

    @Override
    public ResponseEntity<ReadRepoResponseApiModel> readrepo(@Valid ReadRepoRequestApiModel readRepoRequestApiModel) {
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
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
        }
    }
}
