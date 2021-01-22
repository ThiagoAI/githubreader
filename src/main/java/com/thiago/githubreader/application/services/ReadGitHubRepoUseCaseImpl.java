package com.thiago.githubreader.application.services;

import com.thiago.githubreader.application.ports.in.ReadGitHubRepoUseCase;
import com.thiago.githubreader.domain.ConnectionHandler;
import com.thiago.githubreader.domain.ConnectionHandlerImpl;
import com.thiago.githubreader.domain.GitHubRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotBlank;

@Service
@RequiredArgsConstructor
public class ReadGitHubRepoUseCaseImpl implements ReadGitHubRepoUseCase {
    @Override
    public GitHubRepo readGitHubRepo(@NotBlank String gitHubUrl) {
        // Validate input
        if (gitHubUrl == null || gitHubUrl == "")
            return null;

//        UrlValidator urlValidator = new UrlValidator();
//        urlValidator.isValid("http://my favorite site!");

        // Stablishes connection to repo
        ConnectionHandler connectionHandler = new ConnectionHandlerImpl();
        connectionHandler.openConnection(gitHubUrl);

        // Creates repo object
        String html = connectionHandler.httpGetRequestToString(gitHubUrl);
        GitHubRepo gitHubRepo = new GitHubRepo(html, 100);
        connectionHandler.closeConnection();

        return gitHubRepo;
    }
}
