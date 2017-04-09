package com.softwaremill.java_fp_example.contest.mszarlinski.domain.web;

import javaslang.control.Option;

public interface Element {
    boolean hasAttr(String attrName, String attrValue);

    Option<String> attr(String attrName);

    Elements childrenByTag(String tagName);
}
