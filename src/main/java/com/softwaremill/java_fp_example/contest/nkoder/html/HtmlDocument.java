package com.softwaremill.java_fp_example.contest.nkoder.html;

import javaslang.collection.Stream;
import org.jsoup.nodes.Document;

public class HtmlDocument {

    private final Document jsoupDocument;

    public HtmlDocument(Document document) {
        this.jsoupDocument = document;
    }

    public Stream<HtmlMetaTag> metaTags() {
        return Stream.of(jsoupDocument.head().getElementsByTag("meta"))
                .flatMap(elements -> elements)
                .map(element -> new HtmlMetaTag(element));
    }
}
