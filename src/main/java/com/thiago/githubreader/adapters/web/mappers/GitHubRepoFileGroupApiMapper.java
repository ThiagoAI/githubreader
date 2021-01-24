package com.thiago.githubreader.adapters.web.mappers;

import com.thiago.githubreader.adapters.web.model.ReadRepoGroupApiModel;
import com.thiago.githubreader.domain.githubrepo.GitHubRepoFileExtensionList;

import java.math.BigDecimal;
import java.util.stream.Collectors;

public final class GitHubRepoFileGroupApiMapper {
    public static ReadRepoGroupApiModel toReadRepoGroupApiModel(GitHubRepoFileExtensionList gitHubRepoFileExtensionList) {
        return new ReadRepoGroupApiModel()
                .totalSize(String.valueOf(gitHubRepoFileExtensionList.getTotalBytesFormattedString()))
                .lineCount(BigDecimal.valueOf(gitHubRepoFileExtensionList.getTotalLines()))
                .fileExtension(gitHubRepoFileExtensionList.getFileExtension())
                .files(gitHubRepoFileExtensionList.getCopyOfAllFiles().stream().map(
                        file -> GitHubRepoFileApiMapper.toReadRepoFileApiModel(file))
                        .collect(Collectors.toList())
                );
    }
}
