package com.thiago.githubreader.domain.githubscraping.parsers;

import com.thiago.githubreader.domain.FileName;

import javax.validation.constraints.NotNull;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class FileNameParser {
    public static FileName parseToFileName(@NotNull String html) {
        String fileExtension = "";
        String fileName = "";

        try {
            Matcher matcher = Pattern
                    .compile("<strong[^>]*final-path[^>]>[\\n\\s\\t]*(.*)[\\n\\s\\t]*</strong>")
                    .matcher(html);
            matcher.find();
            fileName = matcher.group(1);
            if (fileName.contains("."))
                fileExtension = fileName.substring(fileName.lastIndexOf(".") + 1);
        } catch (Exception e) {
            e.printStackTrace();
            fileExtension = "";
            fileName = "";
        }

        return new FileName(fileName, fileExtension);
    }
}
