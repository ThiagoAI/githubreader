package com.thiago.githubreader.domain.githubscraping.parsers;

import com.thiago.githubreader.domain.LineCount;

import javax.validation.constraints.NotNull;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class LineCountParser {
    public static LineCount parseToLineCount(@NotNull String html) {
        long numberOfLines;

        // If empty, assign 0
        if (html.isBlank()) {
            numberOfLines = 0;
        } else {
            // Gets first number and assigns it as number of lines
            try {
                Matcher matcher = Pattern
                        .compile(">[\\n\\s\\t]*(\\d+)\\slines")
                        .matcher(html);
                matcher.find();
                numberOfLines = Long.parseLong(matcher.group(1));
            } catch (IllegalStateException e) {
                // Some files don't have a number of lines
                numberOfLines = 0;
            } catch (Exception e) {
                // Unexpected
                e.printStackTrace();
                numberOfLines = 0;
            }
        }
        return new LineCount(numberOfLines);
    }
}
