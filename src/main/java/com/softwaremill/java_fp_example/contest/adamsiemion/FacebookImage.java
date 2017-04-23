package com.softwaremill.java_fp_example.contest.adamsiemion;

import static com.softwaremill.java_fp_example.contest.adamsiemion.OpenGraphImage.DEFAULT_IMAGE;
import static java.util.Objects.requireNonNull;

import com.softwaremill.java_fp_example.contest.adamsiemion.WebPageAddress.WebPageDownloadException;

public class FacebookImage {

    private final WebPageAddress webPageAddress;

    public FacebookImage(final WebPageAddress webPageAddress) {
        this.webPageAddress = requireNonNull(webPageAddress);
    }

    public String extractImageAddress() {
        return getImage().content();
    }

    private OpenGraphImage getImage() {
        try {
            return webPageAddress.download().openGraphHeadImages().getOrElse(DEFAULT_IMAGE);
        } catch (WebPageDownloadException e) {
            return DEFAULT_IMAGE;
        }
    }
}
