package com.softwaremill.java_fp_example.contest.dmydlarz.jsoup;

import org.jsoup.nodes.Element;

import java.util.Iterator;

final class JsoupTagElements implements Iterable<Element> {
    private final JsoupDocument jsoupDocument;
    private final String tag;

    JsoupTagElements(JsoupDocument jsoupDocument, String tag) {
        this.jsoupDocument = jsoupDocument;
        this.tag = tag;
    }

    @Override
    public Iterator<Element> iterator() {
        try {
            return jsoupDocument.head().getElementsByTag(tag).iterator();
        } catch (Exception e) {
            throw new RuntimeException(String.format("Can't get %s elements. %s", tag, e.getMessage()), e);
        }
    }
}
