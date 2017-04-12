package com.softwaremill.java_fp_example.contest.barti271.page;

import static com.softwaremill.java_fp_example.DefaultImage.DEFAULT_IMAGE;

import com.softwaremill.java_fp_example.contest.barti271.page.content.Content;
import com.softwaremill.java_fp_example.contest.barti271.page.content.Image;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SimplePage implements Page {

    private final Content content;

    public SimplePage(Content content) {
        this.content = content;
    }

    /**
     * @return URL of first og:image read from the supplied {@link Content} or
     * {@link com.softwaremill.java_fp_example.DefaultImage#DEFAULT_IMAGE} URL when
     * no images can be read.
     */
    @Override
    public String extractImageAddress() {
        try {
            return tryExtractImageAddress();
        } catch (RuntimeException e) {
            log.error("Unable to extract og:image from url {}. Problem: {}",
                e.getMessage(), e.getCause().getMessage());
            return DEFAULT_IMAGE;
        }
    }

    private String tryExtractImageAddress() {
        return content.getOpenGraphImages()
            .findFirst()
            .map(Image::getUrl)
            .orElseGet(this::defaultUrl);
    }

    private String defaultUrl() {
        log.warn("No og:image found for blog post {}", content);
        return DEFAULT_IMAGE;
    }
}
