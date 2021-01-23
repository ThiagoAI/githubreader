package com.thiago.githubreader.domain;

import javax.validation.constraints.NotBlank;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BytesSize {
    // Multipliers for transforming to bytes
    private static final long KB_MULTIPLIER = 1024;
    private static final long MB_MULTIPLIER = 1048576;
    private static final long GB_MULTIPLIER = 1073741824;

    private final double bytes;
    private final @NotBlank String bytesString;

    /**
     * Receives a github html and tries to get size of file
     *
     * @param html input string
     */
    public BytesSize(@NotBlank String html) {

        String bytesString;
        double fileSize = 0;

        // Gets number and extension (Bytes, KB, MB ...)
        String fileSizeExtension = "";
        try {
            Matcher matcher = Pattern
                    .compile(">[\\n\\s\\t]*([\\d\\.^]+)\\s(Bytes|[KMG]B)[\\s\\n\\t]*<")
                    .matcher(html);
            matcher.find();
            fileSize = Double.parseDouble(matcher.group(1));
            fileSizeExtension = matcher.group(2);
            bytesString = fileSize + " " + fileSizeExtension;
        } catch (Exception e) {
            // TODO actually handle null and number format
            e.printStackTrace();
            fileSize = 0;
            bytesString = "0 Bytes";
        }
        this.bytesString = bytesString;

        // Gets multiplier and uses it to store value as bytes
        long multiplier = 0;
        switch (fileSizeExtension){
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
                // TODO add exception
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
