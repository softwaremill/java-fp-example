package com.softwaremill.java_fp_example.contest.barti271.page;

import java.util.function.Supplier;

/**
 * Caches results produced by supplied page.
 * This class is thread-safe.
 */
public class CachedPage implements Page {

    private final Page page;
    private Supplier<String> imageAddress = this::retrieveImageAddress;

    public CachedPage(Page page) {
        this.page = page;
    }

    @Override
    public String extractImageAddress() {
        return imageAddress.get();
    }

    private synchronized String retrieveImageAddress() {
        return isInitialized() ? imageAddress.get() : initialize();
    }

    private boolean isInitialized() {
        return imageAddress instanceof Holder;
    }

    private String initialize() {
        imageAddress = new Holder(page.extractImageAddress());
        return imageAddress.get();
    }

    private static class Holder implements Supplier<String> {

        private final String url;

        private Holder(String url) {
            this.url = url;
        }

        @Override
        public String get() {
            return url;
        }

    }

}
