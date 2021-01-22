package com.thiago.githubreader.application.ports.in;

import com.thiago.githubreader.domain.GitHubRepo;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;

@Validated
public interface ReadGitHubRepoUseCase {
    GitHubRepo readGitHubRepo(@NotBlank String gitHubRepo);
}
