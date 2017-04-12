package com.softwaremill.java_fp_example.contest.barti271.page.content.jspup;

import org.jsoup.nodes.Element;

import com.softwaremill.java_fp_example.contest.barti271.page.content.source.MetaElement;


class JsoupMetaElement implements MetaElement {

    private final Element element;

    JsoupMetaElement(Element element) {
        this.element = element;
    }

    @Override
    public String getContent() {
        return element.attr("content");
    }

    @Override
    public String getProperty() {
        return element.attr("property");
    }

}
