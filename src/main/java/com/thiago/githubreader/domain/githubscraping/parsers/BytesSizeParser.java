package com.thiago.githubreader.domain.githubscraping.parsers;

import com.thiago.githubreader.domain.BytesSize;
import com.thiago.githubreader.domain.bytesutils.BytesConstants;

import javax.validation.constraints.NotNull;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class BytesSizeParser {
    public static BytesSize parseToBytesSize(@NotNull String html) {
        String bytesString;
        double fileSize = 0;
        double bytes = 0;

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
            e.printStackTrace();
            fileSize = 0;
            bytesString = "0 Bytes";
        }
        bytesString = bytesString;

        // Gets multiplier and uses it to store value as bytes
        long multiplier = 0;
        switch (fileSizeExtension) {
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
        bytes = multiplier * fileSize;

        return new BytesSize(bytes, bytesString);
    }
}
