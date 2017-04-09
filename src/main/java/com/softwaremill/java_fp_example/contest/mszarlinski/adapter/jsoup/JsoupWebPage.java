package com.softwaremill.java_fp_example.contest.mszarlinski.adapter.jsoup;

import com.softwaremill.java_fp_example.contest.mszarlinski.domain.web.DummyElement;
import com.softwaremill.java_fp_example.contest.mszarlinski.domain.web.Element;
import com.softwaremill.java_fp_example.contest.mszarlinski.domain.web.WebPage;
import javaslang.control.Option;
import org.jsoup.nodes.Document;

class JsoupWebPage implements WebPage {

    private final Document wrapped;

    JsoupWebPage(Document document) {
        this.wrapped = document;
    }

    @Override
    public Element head() {
        return Option.of(wrapped.head())
                .map(JsoupElement::from)
                .getOrElse(DummyElement.INSTANCE);
    }


}
