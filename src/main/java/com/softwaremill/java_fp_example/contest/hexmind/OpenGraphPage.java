package com.softwaremill.java_fp_example.contest.hexmind;

import java.net.MalformedURLException;
import java.net.UnknownHostException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OpenGraphPage {

    private final ExtractionOptions options;

    public OpenGraphPage(ExtractionOptions options) {
        this.options = options;
    }

    public String extractMetaContent() {
        return new OpenGraphExtractor(options)
            .extractContent()
            .onFailure(this::onFailure)
            .getOrElse(options::getFallbackContent);
    }

    private void onFailure(Throwable error) {
        if (error instanceof MalformedURLException) {
            log.error("Invalid URL {}. Problem: {}", options.getPageUrl(), error.getMessage());
        } else if (error instanceof UnknownHostException) {
            log.error("Unable to extract content from URL {}. Problem: {}", options.getPageUrl(), error.getMessage());
        } else {
            log.warn("No {} found for page {}", options.getMetaProperty(), options.getPageUrl());
        }
    }

}
