package com.thiago.githubreader.domain.githubscraping.parsers;

import com.thiago.githubreader.domain.githubrepo.GitHubRepoFile;

import javax.validation.constraints.NotNull;

public final class GitHubRepoFileParser {

    public static GitHubRepoFile parseToFile(@NotNull String html) {
        return new GitHubRepoFile(
                BytesSizeParser.parseToBytesSize(html),
                LineCountParser.parseToLineCount(html),
                FileNameParser.parseToFileName(html)
        );
    }

    /**
     * Determines if an html represents a github file
     *
     * @param html input html
     * @return true if page is a github file, else false
     */
    public static boolean isPageFile(String html) {
        return html.contains("octicon-trashcan");
    }

}
