package com.softwaremill.java_fp_example.contest.dorzepowski;

import java.net.URL;


public class Page {

	private final URL pageUrl;

	public Page(URL pageUrl) {
		this.pageUrl = pageUrl;
	}

	public Image facebookImage() {
		return FacebookImage.from(new ParsedPage(pageUrl));
	}

}
