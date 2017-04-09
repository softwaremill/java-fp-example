package com.softwaremill.java_fp_example.contest.mszarlinski.adapter.jsoup;

import com.softwaremill.java_fp_example.contest.mszarlinski.domain.web.DummyElement;
import com.softwaremill.java_fp_example.contest.mszarlinski.domain.web.DummyElements;
import com.softwaremill.java_fp_example.contest.mszarlinski.domain.web.Element;
import com.softwaremill.java_fp_example.contest.mszarlinski.domain.web.Elements;
import javaslang.collection.List;

class JsoupElements implements Elements {

    private final List<JsoupElement> wrapped;

    static Elements from(List<JsoupElement> elements) {
        if (elements.isEmpty()) {
            return DummyElements.INSTANCE;
        } else {
            return new JsoupElements(elements);
        }
    }

    private JsoupElements(List<JsoupElement> wrapped) {
        this.wrapped = wrapped;
    }

    @Override
    public Element first() {
        return wrapped.headOption()
                .map(h -> (Element) h)
                .getOrElse(DummyElement.INSTANCE);
    }

    @Override
    public Elements filterByAttribute(String attrName, String attrValue) {
        return JsoupElements.from(wrapped.filter(e -> e.hasAttr(attrName, attrValue)));
    }

}
