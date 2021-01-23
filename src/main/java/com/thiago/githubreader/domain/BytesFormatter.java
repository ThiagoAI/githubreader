package com.thiago.githubreader.domain;

public final class BytesFormatter {
    public static String formatBytes(double bytes) {
        if (bytes < BytesConstants.KB_MULTIPLIER) {
            return String.format("%.2f " + BytesConstants.BYTES_SUFIX, bytes);
        }
        if (bytes < BytesConstants.MB_MULTIPLIER) {
            return String.format("%.2f " + BytesConstants.KB_SUFIX, bytes / BytesConstants.KB_MULTIPLIER);
        }
        if (bytes < BytesConstants.GB_MULTIPLIER) {
            return String.format("%.2f " + BytesConstants.MB_SUFIX, bytes / BytesConstants.MB_MULTIPLIER);
        }
        return String.format("%.2f " + BytesConstants.GB_SUFIX, bytes / BytesConstants.GB_MULTIPLIER);
    }
}
