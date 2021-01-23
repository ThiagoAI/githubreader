package com.thiago.githubreader.domain;

import javax.validation.constraints.NotBlank;

public class GitHubFile {
    private final BytesSize bytesSize;
    private final LineCount lineCount;
    private final FileName fileName;

    public GitHubFile(@NotBlank String html) {
        this.bytesSize = new BytesSize(html);
        this.lineCount = new LineCount(html);
        this.fileName  = new FileName(html);
    }

    public BytesSize getBytesSize() {
        return bytesSize;
    }

    public LineCount getLineCount() {
        return lineCount;
    }

    public String getFileExtension() {
        return fileName.getFileExtension();
    }

    public String getFileName() {
        return fileName.getFileName();
    }
}
