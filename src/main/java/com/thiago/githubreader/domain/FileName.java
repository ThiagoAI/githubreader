package com.thiago.githubreader.domain;

import javax.validation.constraints.NotBlank;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileName {
    String fileName;
    String fileExtension;

    public FileName(@NotBlank String html) {
        this.fileExtension = "";
        try {
            Matcher matcher = Pattern
                    .compile("<strong[^>]*final-path[^>]>[\\n\\s\\t]*(.*)[\\n\\s\\t]*</strong>")
                    .matcher(html);
            matcher.find();
            this.fileName = matcher.group(1);
            if (fileName.contains("."))
                this.fileExtension = fileName.substring(fileName.lastIndexOf(".") + 1);
        } catch (Exception e) {
            // TODO actually handle
            e.printStackTrace();
            this.fileExtension = "";
            this.fileName = "";
        }
    }

    public String getFileName() {
        return fileName;
    }

    public String getFileExtension() {
        return fileExtension;
    }
}
