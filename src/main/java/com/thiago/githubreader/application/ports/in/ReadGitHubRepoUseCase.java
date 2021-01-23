package com.thiago.githubreader.application.ports.in;

import com.thiago.githubreader.domain.GitHubRepo;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import java.util.concurrent.CompletableFuture;

@Validated
public interface ReadGitHubRepoUseCase {
    CompletableFuture<GitHubRepo> readGitHubRepo(@NotBlank String gitHubRepo);
}
