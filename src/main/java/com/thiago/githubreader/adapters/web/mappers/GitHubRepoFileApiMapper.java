package com.thiago.githubreader.adapters.web.mappers;

import com.thiago.githubreader.adapters.web.model.ReadRepoFileApiModel;
import com.thiago.githubreader.domain.githubrepo.GitHubRepoFile;

import java.math.BigDecimal;

public final class GitHubRepoFileApiMapper {
    public static ReadRepoFileApiModel toReadRepoFileApiModel(GitHubRepoFile gitHubRepoFile) {
        return new ReadRepoFileApiModel()
                .lineCount(BigDecimal.valueOf(gitHubRepoFile.getLineCount().getNumberOfLines()))
                .name(gitHubRepoFile.getFileName())
                .size(gitHubRepoFile.getBytesSize().getBytesString());
    }
}
