package com.thiago.githubreader.adapters.web.mappers;

import com.thiago.githubreader.adapters.web.model.ReadRepoFileApiModel;
import com.thiago.githubreader.domain.githubrepo.GitHubFile;

import java.math.BigDecimal;

public final class GitHubRepoFileApiMapper {
    public static ReadRepoFileApiModel toReadRepoFileApiModel(GitHubFile gitHubFile){
        return new ReadRepoFileApiModel()
                .lineCount(BigDecimal.valueOf(gitHubFile.getLineCount().getNumberOfLines()))
                .name(gitHubFile.getFileName())
                .size(gitHubFile.getBytesSize().getBytesString());
    }
}
