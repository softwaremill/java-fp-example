package com.softwaremill.java_fp_example.contest.nkoder.html;

import org.jsoup.nodes.Element;

import static java.lang.String.format;

public class HtmlMetaTag {

    private final Element jsoupElement;

    HtmlMetaTag(Element element) {
        validate(element);
        this.jsoupElement = element;
    }

    private void validate(Element element) {
        if (!element.tagName().equals("meta")) {
            throw new RuntimeException(format(
                    "Element has to be a 'meta' tag, but is '%s'.",
                    element.tagName()
            ));
        }
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
