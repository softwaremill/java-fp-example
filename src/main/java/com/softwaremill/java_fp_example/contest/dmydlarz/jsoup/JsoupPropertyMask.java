package com.softwaremill.java_fp_example.contest.dmydlarz.jsoup;

import org.jsoup.nodes.Element;

import java.util.function.Predicate;

public class JsoupPropertyMask implements Predicate<Element> {
    private static final String PROPERTY = "property";
    private final String expectedProperty;

    JsoupPropertyMask(String expectedProperty) {
        this.expectedProperty = expectedProperty;
    }

    @Override
    public boolean test(Element element) {
        return expectedProperty.equalsIgnoreCase(element.attr(PROPERTY));
    }
}
