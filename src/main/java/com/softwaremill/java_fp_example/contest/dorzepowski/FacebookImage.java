package com.softwaremill.java_fp_example.contest.dorzepowski;

import javaslang.control.Option;

class FacebookImage implements Image {

	private final static String FACEBOOK_IMAGE_TAG = "og:image";

	private final ParsedPage parsedPage;

	private FacebookImage(ParsedPage parsedPage) {
		this.parsedPage = parsedPage;
	}

	static FacebookImage from(ParsedPage parsedPage) {
		return new FacebookImage(parsedPage);
	}

	@Override
	public ImageUrl url() {
		return facebookImageUrl().getOrElse(DefaultImageUrl::new);
	}

	private Option<ImageUrl> facebookImageUrl() {
		return imageUrlOf(contentFrom(firstImageElement()));
	}

	private Option<ImageUrl> imageUrlOf(Option<String> url) {
		return url.map(FacebookImageUrl::new);
	}

	private Option<String> contentFrom(Option<ParsedElement> parsedElements) {
		return parsedElements.map(ParsedElement::content);
	}

	private Option<ParsedElement> firstImageElement() {
		return parsedPage.elementsByProperty(FACEBOOK_IMAGE_TAG).headOption();
	}

}
