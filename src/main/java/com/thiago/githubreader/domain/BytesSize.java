package com.thiago.githubreader.domain;

import javax.validation.constraints.NotBlank;

public final class BytesSize {
    private final double bytes;
    private final @NotBlank String bytesString;

    public BytesSize(double bytes, @NotBlank String bytesString) {
        this.bytes = bytes;
        this.bytesString = bytesString;
    }

    public double getBytes() {
        return bytes;
    }

    public String getBytesString() {
        return bytesString;
    }
}
