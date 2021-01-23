package com.thiago.githubreader.adapters.web.mappers;

import com.thiago.githubreader.adapters.web.model.ReadRepoGroupApiModel;
import com.thiago.githubreader.domain.GitHubRepoFileExtensionList;

import java.math.BigDecimal;
import java.util.stream.Collectors;

public final class GitHubRepoFileGroupApiMapper {
    public static ReadRepoGroupApiModel toReadRepoGroupApiModel(GitHubRepoFileExtensionList gitHubRepoFileExtensionList) {
        return new ReadRepoGroupApiModel()
                .totalSize(String.valueOf(gitHubRepoFileExtensionList.getTotalBytes()))
                .lineCount(BigDecimal.valueOf(gitHubRepoFileExtensionList.getTotalLines()))
                .fileExtension(gitHubRepoFileExtensionList.getFileExtension())
                .files(gitHubRepoFileExtensionList.getList().stream().map(
                        file -> GitHubRepoFileApiMapper.toReadRepoFileApiModel(file))
                        .collect(Collectors.toList())
                );
    }
}
