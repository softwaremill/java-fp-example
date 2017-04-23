package com.softwaremill.java_fp_example.contest.hexmind;

import com.softwaremill.java_fp_example.DefaultImage;

public class FacebookImage {

    private static final int TIMEOUT_MS = 10_000;
    private static final String META_PROPERTY = "og:image";

    private final OpenGraphPage page;

    public FacebookImage(ExtractionOptions options) {
        this.page = new OpenGraphPage(options);
    }

    public static FacebookImage fromPage(String pageUrl) {
        return new FacebookImage(setupForPage(pageUrl));
    }

    public String extractUrl() {
        return page.extractMetaContent();
    }

    private static ExtractionOptions setupForPage(String pageUrl) {
        return ExtractionOptions.builder()
            .pageUrl(pageUrl)
            .timeoutMs(TIMEOUT_MS)
            .metaProperty(META_PROPERTY)
            .fallbackContent(DefaultImage.DEFAULT_IMAGE)
            .build();
    }

}
