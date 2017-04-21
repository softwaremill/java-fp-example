package com.softwaremill.java_fp_example.contest.dorzepowski;

import org.jsoup.nodes.Element;

class ParsedElement {

	private final Element element;

	ParsedElement(Element element) {
		this.element = element;
	}

	String content() {
		return element.attr("content");
	}
}
