package com.thiago.githubreader.domain;

import javax.validation.constraints.NotNull;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class LineCount {
    private final long numberOfLines;

    /**
     * Receives github html and tries to get number of lines
     *
     * @param linesString input string
     */
    public LineCount(@NotNull String linesString) {
        long numberOfLines;
        // If empty, assign 0
        if (linesString.isBlank()) {
            numberOfLines = 0;
        } else {
            // Gets first number and assigns it as number of lines
            try {
                Matcher matcher = Pattern
                        .compile(">[\\n\\s\\t]*(\\d+)\\slines")
                        .matcher(linesString);
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
        this.numberOfLines = numberOfLines;
    }

    public long getNumberOfLines() {
        return numberOfLines;
    }
}
