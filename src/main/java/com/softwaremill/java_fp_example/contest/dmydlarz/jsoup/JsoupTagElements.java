package com.softwaremill.java_fp_example.contest.dmydlarz.jsoup;

import org.jsoup.nodes.Element;

import java.util.Iterator;

final class JsoupTagElements implements Iterable<Element> {
    private final Document document;
    private final String tag;

    JsoupTagElements(Document document, String tag) {
        this.document = document;
        this.tag = tag;
    }

    @Override
    public Iterator<Element> iterator() {
        try {
            return document.head().getElementsByTag(tag).iterator();
        } catch (Exception e) {
            throw new RuntimeException(String.format("Can't get HEAD element. %s", e.getMessage()), e);
        }
    }
}
