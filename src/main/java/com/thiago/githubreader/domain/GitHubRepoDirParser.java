package com.thiago.githubreader.domain;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GitHubRepoDirParser {
    private static String GITHUB_BASE_URL = "https://github.com";

    /**
     * Reads a github dir html and gets all urls for files and dirs within that dir
     *
     * @param html github repo dir html
     * @return list of dir/file urls contained in the dir
     */
    public static List<String> getDirOrFileUrls(@NotBlank String html) {
        ArrayList<String> list = new ArrayList();
        // Gets all urls that fit the pattern
        Matcher matcher = Pattern.compile(
                "<a.*js-navigation-open.*href=\"(.*?)\""
        ).matcher(html);

        // Add tem to the list
        while (matcher.find()) {
            if (matcher.group(1) != "")
                list.add(GITHUB_BASE_URL + matcher.group(1));
        }

        return list;
    }
}
