package com.thiago.githubreader.domain;

import javax.validation.constraints.NotBlank;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class BytesSize {
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
            case BytesConstants.BYTES_SUFIX:
                multiplier = 1;
                break;
            case BytesConstants.KB_SUFIX:
                multiplier = BytesConstants.KB_MULTIPLIER;
                break;
            case BytesConstants.MB_SUFIX:
                multiplier = BytesConstants.MB_MULTIPLIER;
                break;
            case BytesConstants.GB_SUFIX:
                multiplier = BytesConstants.GB_MULTIPLIER;
                break;
            default:
                multiplier = 0;
                break;
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
