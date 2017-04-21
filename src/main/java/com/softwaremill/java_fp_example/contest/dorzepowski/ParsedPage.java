package com.softwaremill.java_fp_example.contest.dorzepowski;

import java.io.IOException;
import java.net.URL;
import java.util.function.Function;

import javaslang.collection.Stream;
import javaslang.control.Try;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

@Slf4j
class ParsedPage {

	private final static int TEN_SECONDS = 10_000;

	private final URL pageUrl;

	ParsedPage(URL pageUrl) {
		this.pageUrl = pageUrl;
	}

	Stream<ParsedElement> elementsByProperty(String property) {
		return makeParsedElementsWhen(onPage(foundElementsWithProperty(property)));
	}

	private Stream<ParsedElement> makeParsedElementsWhen(Stream<Element> foundElements) {
		return foundElements.map(ParsedElement::new);
	}

	private Stream<Element> onPage(Function<? super Document, ? extends Elements> toFoundElements) {
		return parsePage().flatMap(toFoundElements);
	}

	private Function<? super Document, ? extends Elements> foundElementsWithProperty(String property) {
		return document -> document.head().getElementsByAttributeValue("property", property);
	}

	private Stream<Document> parsePage() {
		return Try.of(this::parse).onFailure(this::logParsingProblem).toStream();
	}

	private Document parse() throws IOException {
		return Jsoup.parse(pageUrl, TEN_SECONDS);
	}

	private void logParsingProblem(Throwable e) {
		log.error("Unable to extract og:image from url {}. Problem: {}", pageUrl, e.getMessage());
	}
}
