package com.softwaremill.java_fp_example.contest.dorzepowski;

class FacebookImageUrl implements ImageUrl {

	private final String url;

	FacebookImageUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return url;
	}
}
