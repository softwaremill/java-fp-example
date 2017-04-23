package com.softwaremill.java_fp_example.contest.adamsiemion;

import static java.util.Objects.requireNonNull;

import javaslang.collection.List;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

class WebPage {

    private static final String OPENGRAPH_IMAGE_TAG = "og:image";

    private final Document document;

    WebPage(final Document document) {
        this.document = requireNonNull(document);
    }

    List<OpenGraphImage> openGraphHeadImages() {
        return List.ofAll(head().getElementsByTag("meta"))
                .filter(e -> OPENGRAPH_IMAGE_TAG.equals(e.attr("property")))
                .map(ParsedOpenGraphImage::new);
    }

    private Element head() {
        return document.head();
    }
}
