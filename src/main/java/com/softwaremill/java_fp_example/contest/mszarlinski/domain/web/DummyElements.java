package com.softwaremill.java_fp_example.contest.mszarlinski.domain.web;

public class DummyElements implements Elements {

    public static final Elements INSTANCE = new DummyElements();

    @Override
    public Element first() {
        return DummyElement.INSTANCE;
    }

    @Override
    public Elements filterByAttribute(String attrName, String attrValue) {
        return this;
    }
}
