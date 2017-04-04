package com.softwaremill.java_fp_example.contest.dmydlarz;

import lombok.extern.slf4j.Slf4j;

public interface WebPage {
    String facebookImage() throws Exception;

    @Slf4j
    final class Safe implements WebPage {
        private final WebPage origin;
        private final String defaultImage;

        public Safe(WebPage origin, String defaultImage) {
            this.origin = origin;
            this.defaultImage = defaultImage;
        }

        @Override
        public String facebookImage() {
            try {
                return origin.facebookImage();
            } catch (Exception e) {
                log.error("Unable to serve Open Graph Image. Problem: {}", e.getMessage());
                return defaultImage;
            }
        }
    }
}
