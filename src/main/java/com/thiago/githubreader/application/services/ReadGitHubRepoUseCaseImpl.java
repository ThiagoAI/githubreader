package com.thiago.githubreader.application.services;

import com.thiago.githubreader.application.ports.in.ReadGitHubRepoUseCase;
import com.thiago.githubreader.domain.GitHubConstants;
import com.thiago.githubreader.domain.GitHubRepo;
import com.thiago.githubreader.domain.GitHubRepoConnectionHandler;
import com.thiago.githubreader.domain.GitHubRepoConnectionHandlerImpl;
import lombok.RequiredArgsConstructor;
import org.apache.http.conn.HttpClientConnectionManager;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.validation.ValidationException;
import javax.validation.constraints.NotBlank;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class ReadGitHubRepoUseCaseImpl implements ReadGitHubRepoUseCase {
    private final HttpClientConnectionManager httpClientConnectionManager;

    @Async
    @Override
    public CompletableFuture<GitHubRepo> readGitHubRepo(@NotBlank String gitHubUrl) throws URISyntaxException {
        // Validate input
        if (gitHubUrl == null || gitHubUrl == "" ||
                !new URI(gitHubUrl).getHost().equals(GitHubConstants.GITHUB_HOST))
            throw new ValidationException();

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
