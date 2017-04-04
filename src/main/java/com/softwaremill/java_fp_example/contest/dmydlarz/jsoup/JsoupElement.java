package com.softwaremill.java_fp_example.contest.dmydlarz.jsoup;

import com.softwaremill.java_fp_example.contest.dmydlarz.functional.UnsafeSupplier;
import org.jsoup.nodes.Element;

final class JsoupElement {
    private final UnsafeSupplier<Element> element;

    JsoupElement(UnsafeSupplier<Element> element) {
        this.element = element;
    }

    String content() throws Exception {
        try {
            return element.get().attr("content");
        } catch (Exception e) {
            throw new Exception(String.format("Can't get element. %s", e.getMessage()), e);
        }
    }
}
