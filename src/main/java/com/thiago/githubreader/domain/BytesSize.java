package com.thiago.githubreader.domain;

import javax.validation.constraints.NotBlank;
import java.util.regex.Pattern;

public class BytesSize {
    // Multipliers for transforming to bytes
    private static final long KB_MULTIPLIER = 1024;
    private static final long MB_MULTIPLIER = 1048576;
    private static final long GB_MULTIPLIER = 1073741824;

    private final double bytes;
    private final @NotBlank String bytesString;

    /**
     * Receives a string like "15.01 KB" or "425 Bytes" and process it into number of bytes
     *
     * @param bytesString input string
     */
    public BytesSize(@NotBlank String bytesString) {
        this.bytesString = bytesString;

        // Gets number and extension (Bytes, KB, MB ...)
        double fileSize = 0;
        try {
            fileSize = Double.parseDouble(
                    Pattern.compile("([\\d\\.]*)").matcher(bytesString).group(1)
            );
        } catch (Exception e) {
            // TODO actually handle null and number format
            fileSize = 0;
        }

        String extension = bytesString.replaceAll("([\\d\\.]*)", "").trim();
        // Gets multiplier and uses it to store value as bytes
        long multiplier = 1;
        switch (extension){
            case "Bytes":
                multiplier = 1;
                break;
            case "KB":
                multiplier = KB_MULTIPLIER;
            case "MB":
                multiplier = MB_MULTIPLIER;
            case "GB":
                multiplier = GB_MULTIPLIER;
            default:
                multiplier = 0;
        }
        this.bytes = multiplier*fileSize;
    }

    public double getBytes() {
        return bytes;
    }

    public String getBytesString() {
        return bytesString;
    }
}
