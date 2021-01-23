package com.thiago.githubreader.adapters.web.mappers;

import com.thiago.githubreader.adapters.web.model.ReadRepoFileApiModel;
import com.thiago.githubreader.adapters.web.model.ReadRepoGroupApiModel;
import com.thiago.githubreader.domain.GitHubFile;
import com.thiago.githubreader.domain.GitHubRepoFileExtensionList;

import java.math.BigDecimal;

public final class GitHubRepoFileApiMapper {
    public static ReadRepoFileApiModel toReadRepoFileApiModel(GitHubFile gitHubFile){
        return new ReadRepoFileApiModel()
                .lineCount(BigDecimal.valueOf(gitHubFile.getLineCount().getNumberOfLines()))
                .name(gitHubFile.getFileName())
                .size(gitHubFile.getBytesSize().getBytesString());
    }
}
