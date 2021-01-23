package com.thiago.githubreader.adapters.web.mappers;

import com.thiago.githubreader.adapters.web.model.ReadRepoResponseApiModel;
import com.thiago.githubreader.domain.GitHubRepo;

import java.math.BigDecimal;
import java.util.stream.Collectors;

public final class GitHubRepoApiMapper {
    public static ReadRepoResponseApiModel toGitHubRepoModel(GitHubRepo gitHubRepo) {
        return new ReadRepoResponseApiModel()
                .totalSize(String.valueOf(gitHubRepo.getTotalBytes()))
                .totalLines(BigDecimal.valueOf(gitHubRepo.getTotalLines()))
                .fileGroups(gitHubRepo.getGitHubRepoFileContainer().getMap().values()
                        .stream().map(list -> GitHubRepoFileGroupApiMapper
                                .toReadRepoGroupApiModel(list)).collect(Collectors.toList())
                );
    }
}
