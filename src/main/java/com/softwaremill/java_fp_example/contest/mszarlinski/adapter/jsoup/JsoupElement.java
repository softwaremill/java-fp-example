package com.softwaremill.java_fp_example.contest.mszarlinski.adapter.jsoup;

import com.google.common.base.Strings;
import com.softwaremill.java_fp_example.contest.mszarlinski.domain.web.Element;
import com.softwaremill.java_fp_example.contest.mszarlinski.domain.web.Elements;
import javaslang.collection.List;
import javaslang.control.Option;

class JsoupElement implements Element {

    private final org.jsoup.nodes.Element wrapped;

    static Element from(org.jsoup.nodes.Element element) {
        return new JsoupElement(element);
    }

    private JsoupElement(org.jsoup.nodes.Element element) {
        this.wrapped = element;
    }

    @Override
    public boolean hasAttr(String attrName, String attrValue) {
        return attr(attrName)
                .filter(val -> val.equals(attrValue))
                .isDefined();
    }

    @Override
    public Option<String> attr(String attrName) {
        return Option.of(wrapped.attr(attrName))
                .filter(s -> !Strings.isNullOrEmpty(s));
    }

    @Override
    public Elements childrenByTag(String tagName) {
        return JsoupElements.from(wrapped.getElementsByTag(tagName)
                .stream()
                .map(JsoupElement::new)
                .collect(List.collector()));
    }
}
