package com.thiago.githubreader.application.exceptions;

public class GetScrapeRequestFailed extends RuntimeException {
    public GetScrapeRequestFailed(String url, int statusCode) {
        super("Scraping request to \"" + url
                + "\" failed with status code: " + statusCode);
    }

    public GetScrapeRequestFailed(String url, Exception e) {
        super("Scraping request to \"" + url
                + "\" failed. Underlying exception is: " + e.getMessage());
    }
}

