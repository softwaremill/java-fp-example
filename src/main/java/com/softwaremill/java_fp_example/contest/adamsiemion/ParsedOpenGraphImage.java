package com.softwaremill.java_fp_example.contest.adamsiemion;

import org.jsoup.nodes.Element;

class ParsedOpenGraphImage implements OpenGraphImage {

    private final Element imageTag;

    ParsedOpenGraphImage(final Element imageTag) {
        this.imageTag = imageTag;
    }

    @Override
    public String content() {
        return imageTag.attr("content");
    }
}
