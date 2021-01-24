package com.thiago.githubreader.application.services;

import com.thiago.githubreader.application.exceptions.FailedToGetGitHubFilesException;
import com.thiago.githubreader.application.ports.in.ReadGitHubRepoUseCase;
import com.thiago.githubreader.domain.GitHubConstants;
import com.thiago.githubreader.domain.connectionhandler.GitHubRepoConnectionHandler;
import com.thiago.githubreader.domain.connectionhandler.GitHubRepoConnectionHandlerImpl;
import com.thiago.githubreader.domain.githubrepo.GitHubRepo;
import com.thiago.githubreader.domain.githubscraping.GitHubRepoFileGetter;
import org.apache.http.conn.HttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.validation.ValidationException;
import javax.validation.constraints.NotBlank;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.CompletableFuture;

@Service
public class ReadGitHubRepoUseCaseImpl implements ReadGitHubRepoUseCase {
    private final HttpClientConnectionManager httpClientConnectionManager;
    private final Double maxRequestsPerSecond;

    public ReadGitHubRepoUseCaseImpl(
            HttpClientConnectionManager httpClientConnectionManager,
            @Value("${readRepo.maxRequestsPerSecond}") Double maxRequestsPerSecond) {
        this.httpClientConnectionManager = httpClientConnectionManager;
        this.maxRequestsPerSecond = maxRequestsPerSecond;
    }

    @Async
    @Override
    public CompletableFuture<GitHubRepo> readGitHubRepo(@NotBlank String gitHubUrl) throws URISyntaxException {
        // Validates input
        if (gitHubUrl == null || gitHubUrl == "" ||
                !new URI(gitHubUrl).getHost().equals(GitHubConstants.GITHUB_HOST))
            throw new ValidationException();

        // Stablishes connection to repo
        GitHubRepo gitHubRepo = null;
        GitHubRepoConnectionHandler gitHubRepoConnectionHandler =
                new GitHubRepoConnectionHandlerImpl(
                        httpClientConnectionManager,
                        this.maxRequestsPerSecond
                );
        gitHubRepoConnectionHandler.openConnection();

        // Scrapes github repo
        try {
            gitHubRepo = new GitHubRepo(gitHubUrl, gitHubRepoConnectionHandler);
            GitHubRepoFileGetter.GetFilesInfoFromRepo(gitHubRepo, gitHubRepoConnectionHandler);
        } catch (Exception e) {
            e.printStackTrace();
            throw new FailedToGetGitHubFilesException(gitHubUrl);
        } finally {
            gitHubRepoConnectionHandler.closeConnection();
        }

        return CompletableFuture.completedFuture(gitHubRepo);
    }
}
