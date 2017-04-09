package com.softwaremill.java_fp_example.contest.mszarlinski.domain.web;

import javaslang.control.Option;

public class DummyElement implements Element {

    public static final Element INSTANCE = new DummyElement();

    @Override
    public boolean hasAttr(String attrName, String attrValue) {
        return false;
    }

    @Override
    public Option<String> attr(String attrName) {
        return Option.none();
    }

    @Override
    public Elements childrenByTag(String tagName) {
        return DummyElements.INSTANCE;
    }
}
