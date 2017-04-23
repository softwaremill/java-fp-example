package com.softwaremill.java_fp_example.contest.adamsiemion;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.net.URL;
import org.jsoup.Jsoup;

class WebPageAddress {

    private static final int DOWNLOAD_TIMEOUT_IN_MS = 10_000;

    private final String url;

    static class WebPageDownloadException extends Exception {

        WebPageDownloadException(final String message, final IOException cause) {
            super(message, cause);
        }
    }

    WebPageAddress(final String url) {
        this.url = requireNonNull(url);
    }

    WebPage download() throws WebPageDownloadException {
        try {
            return new WebPage(Jsoup.parse(new URL(url), DOWNLOAD_TIMEOUT_IN_MS));
        } catch (IOException e) {
            throw new WebPageDownloadException("Could not download page " + url, e);
        }
    }
}
