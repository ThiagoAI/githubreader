package com.thiago.githubreader.adapters.web.mappers;

import com.thiago.githubreader.adapters.web.model.ReadRepoResponseApiModel;
import com.thiago.githubreader.domain.GitHubRepo;

import java.math.BigDecimal;

public final class GitHubRepoApiMapper {
    public static ReadRepoResponseApiModel toGitHubRepoModel(GitHubRepo gitHubRepo){
        return new ReadRepoResponseApiModel()
                .totalLines(BigDecimal.valueOf(gitHubRepo.getTotalLineCount()))
                .totalSize(gitHubRepo.getTotalByteSize());
    }
}
