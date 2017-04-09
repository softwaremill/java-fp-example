package com.softwaremill.java_fp_example.contest.mszarlinski.domain.web;

public interface Elements {

    Element first();

    Elements filterByAttribute(String attrName, String attrValue);
}
