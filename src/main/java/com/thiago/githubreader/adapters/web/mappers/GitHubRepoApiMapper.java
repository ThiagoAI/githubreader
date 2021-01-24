package com.thiago.githubreader.adapters.web.mappers;

import com.thiago.githubreader.adapters.web.model.ReadRepoResponseApiModel;
import com.thiago.githubreader.domain.githubrepo.GitHubRepo;

import java.math.BigDecimal;
import java.util.stream.Collectors;

public final class GitHubRepoApiMapper {
    public static ReadRepoResponseApiModel toGitHubRepoModel(GitHubRepo gitHubRepo) {
        return new ReadRepoResponseApiModel()
                .totalSize(String.valueOf(gitHubRepo.getTotalBytesFormattedString()))
                .totalLines(BigDecimal.valueOf(gitHubRepo.getTotalLines()))
                .fileCount(BigDecimal.valueOf(gitHubRepo.getFileCount()))
                .fileGroups(gitHubRepo.getCopyListOfFileExtensionGroups()
                        .stream().map(list -> GitHubRepoFileGroupApiMapper
                                .toReadRepoGroupApiModel(list)).collect(Collectors.toList())
                );
    }
}
