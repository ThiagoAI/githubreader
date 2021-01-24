package com.thiago.githubreader.domain.githubrepo;

import com.thiago.githubreader.domain.BytesSize;
import com.thiago.githubreader.domain.FileName;
import com.thiago.githubreader.domain.LineCount;

public class GitHubRepoFile {
    private final BytesSize bytesSize;
    private final LineCount lineCount;
    private final FileName fileName;

    public GitHubRepoFile(BytesSize bytesSize,
                          LineCount lineCount,
                          FileName fileName) {
        this.bytesSize = bytesSize;
        this.lineCount = lineCount;
        this.fileName = fileName;
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
