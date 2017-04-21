package com.softwaremill.java_fp_example.contest.nkoder.html;

import org.jsoup.nodes.Element;

public class HtmlMetaTag {

    private final Element jsoupElement;

    HtmlMetaTag(Element element) {
        this.jsoupElement = element;
    }

    public boolean hasProperty(String propertyToMatch) {
        return property().equals(propertyToMatch);
    }

    public String content() {
        return jsoupElement.attr("content");
    }

    private String property() {
        return jsoupElement.attr("property");
    }

}
