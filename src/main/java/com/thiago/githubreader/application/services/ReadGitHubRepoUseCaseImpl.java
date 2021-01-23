package com.thiago.githubreader.application.services;

import com.thiago.githubreader.application.ports.in.ReadGitHubRepoUseCase;
import com.thiago.githubreader.domain.GitHubRepo;
import com.thiago.githubreader.domain.GitHubRepoConnectionHandler;
import com.thiago.githubreader.domain.GitHubRepoConnectionHandlerImpl;
import lombok.RequiredArgsConstructor;
import org.apache.http.conn.HttpClientConnectionManager;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotBlank;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class ReadGitHubRepoUseCaseImpl implements ReadGitHubRepoUseCase {
    private final HttpClientConnectionManager httpClientConnectionManager;

    @Async
    @Override
    public CompletableFuture<GitHubRepo> readGitHubRepo(@NotBlank String gitHubUrl) {
        // Validate input
        if (gitHubUrl == null || gitHubUrl == "")
            return null;

//        UrlValidator urlValidator = new UrlValidator();
//        urlValidator.isValid("http://my favorite site!");

        // Stablishes connection to repo
        GitHubRepo gitHubRepo;
        GitHubRepoConnectionHandler gitHubRepoConnectionHandler =
                new GitHubRepoConnectionHandlerImpl(httpClientConnectionManager);
        gitHubRepoConnectionHandler.openConnection();
        try {
            gitHubRepo = new GitHubRepo(gitHubUrl, gitHubRepoConnectionHandler);
        } finally {
            gitHubRepoConnectionHandler.closeConnection();
        }

        return CompletableFuture.completedFuture(gitHubRepo);
    }
}
