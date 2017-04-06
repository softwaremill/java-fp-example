package com.softwaremill.java_fp_example.contest.maciejdobrowolski;

import com.github.florent37.retrojsoup.annotations.JsoupAttr;

class MetaEntry {

    @JsoupAttr(value = "meta", attr = "property")
    String attr;

    @JsoupAttr(value = "meta", attr = "content")
    String content;

    MetaEntry() {
    }

    MetaEntry(String attr, String content) {
        this.attr = attr;
        this.content = content;
    }
}
