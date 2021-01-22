package com.thiago.githubreader.domain;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.regex.Pattern;

public class LineCount {
    private final long numberOfLines;

    /**
     * Receives a string like "21 lines (17 sloc)" and processes it into the number of lines
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
                numberOfLines = Long.parseLong(
                        Pattern.compile("([\\d]*)").matcher(linesString).group(1)
                );
            } catch (Exception e) {
                // TODO handle null and number format
                numberOfLines = 0;
            }
        }
        this.numberOfLines = numberOfLines;
    }
}
