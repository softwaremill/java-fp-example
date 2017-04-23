package com.softwaremill.java_fp_example.contest.hexmind;

import java.io.IOException;
import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javaslang.collection.List;
import javaslang.control.Try;

public class OpenGraphExtractor {

    private final ExtractionOptions options;

    public OpenGraphExtractor(ExtractionOptions options) {
        this.options = options;
    }

    public Try<String> extractContent() {
        return Try.of(this::loadWebPage)
            .map(this::getMetaElements)
            .flatMap(this::findContent);
    }

    private Document loadWebPage() throws IOException {
        URL url = new URL(options.getPageUrl());
        return Jsoup.parse(url, options.getTimeoutMs());
    }

    private Elements getMetaElements(Document document) {
        return document.head().getElementsByTag("meta");
    }

    private Try<String> findContent(Elements elements) {
        return List.ofAll(elements)
            .filter(this::hasMetaProperty)
            .map(element -> element.attr("content"))
            .toTry();
    }

    private boolean hasMetaProperty(Element element) {
        return options.getMetaProperty().equals(element.attr("property"));
    }
}
