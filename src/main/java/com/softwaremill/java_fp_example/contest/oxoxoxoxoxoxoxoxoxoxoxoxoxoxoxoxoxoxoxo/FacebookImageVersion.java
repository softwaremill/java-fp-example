package com.softwaremill.java_fp_example.contest.oxoxoxoxoxoxoxoxoxoxoxoxoxoxoxoxoxoxoxo;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;

public class FacebookImageVersion {

	public static final String DEFAULT_IMAGE = "https://softwaremill.com/images/logo-vertical.023d8496.png";
	private static final Pattern FACEBOOK_IMAGE_PATTERN = Pattern.compile("<head>.*?(<meta\\s+[^>]*?property=\"og:image\".*?\\/?>).*?<\\/head>", Pattern.CASE_INSENSITIVE | Pattern.DOTALL | Pattern.MULTILINE);
	private static final Pattern TAG_CONTENT_PATTERN = Pattern.compile("content=\"(.+?)\"", Pattern.CASE_INSENSITIVE | Pattern.DOTALL | Pattern.MULTILINE);
	private static final int TEN_SECONDS = 10_000;
	private static final Logger log = Logger.getLogger(FacebookImageVersion.class.getName());

	public static String extractImageAddressFrom(String pageUrl) {
		HttpURLConnection c = null;
		try {
			c = (HttpURLConnection) new URL(pageUrl).openConnection();
		} catch (IOException e) {
			log.severe("Cannot open connection to " + pageUrl + " - reason: " + e.toString());
			return DEFAULT_IMAGE;
		}
		c.setConnectTimeout(TEN_SECONDS);
		c.setReadTimeout(TEN_SECONDS);
		String contentEncoding = c.getHeaderField("Content-Encoding");
		String html = null;
		try (InputStream is = contentEncoding != null && contentEncoding.contains("gzip") ? new GZIPInputStream(c.getInputStream()) : c.getInputStream();
			 Scanner s = new Scanner(is, StandardCharsets.UTF_8.name())) {
			html = s.useDelimiter("\\A").next();
		} catch (IOException e) {
			log.severe("Cannot read from " + pageUrl + " - reason: " + e.toString());
			return DEFAULT_IMAGE;
		}
		try {
			Matcher tagMatch = FACEBOOK_IMAGE_PATTERN.matcher(html);
			tagMatch.find();
			Matcher contentMatch = TAG_CONTENT_PATTERN.matcher(tagMatch.group(1));
			contentMatch.find();
			return contentMatch.group(1);
		} catch (Exception e) {
			log.warning("No og:image found for blog post " + pageUrl);
			return DEFAULT_IMAGE;
		}
	}

}
