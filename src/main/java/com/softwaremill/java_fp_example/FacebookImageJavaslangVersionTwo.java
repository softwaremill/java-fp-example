package com.softwaremill.java_fp_example;

import javaslang.collection.List;
import javaslang.control.Try;
import javaslang.control.Try.CheckedFunction;
import javaslang.control.Try.CheckedSupplier;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.net.URL;
import java.util.function.Consumer;
import java.util.function.Function;

import static javaslang.collection.List.ofAll;

@Slf4j
public class FacebookImageJavaslangVersionTwo {

    private final static String FACEBOOK_IMAGE_TAG = "og:image";
    private final static String DEFAULT_IMAGE = "https://softwaremill.com/images/logo-vertical.023d8496.png";
    private final static int TEN_SECONDS = 10_000;

    public String extractImageAddressFrom(String pageUrl) {
        CheckedSupplier<Document> parseDocument = () -> Jsoup.parse(new URL(pageUrl), TEN_SECONDS);
        CheckedFunction<Document, List<Element>> findElementsWithPropertyTag =
                document -> ofAll(document.head().getElementsByTag("meta"));
        CheckedFunction<List<Element>, List<Element>> findElementsWithFacebookImageProperty =
                elements -> elements.filter(e -> FACEBOOK_IMAGE_TAG.equals(e.attr("property")));
        Consumer<List<Element>> warnIfEmpty = elements -> {
            if (elements.isEmpty()) {
                log.warn("No {} found for blog post {}", FACEBOOK_IMAGE_TAG, pageUrl);
            }
        };
        CheckedFunction<List<Element>, Element> findFirst = elements -> elements.get(0);
        Function<Element, String> content = getContentValue -> getContentValue.attr("content");

        return Try.of(parseDocument)
            .mapTry(findElementsWithPropertyTag)
            .mapTry(findElementsWithFacebookImageProperty)
            .peek(warnIfEmpty)
            .mapTry(findFirst)
            .toOption()
            .map(content)
            .getOrElse(DEFAULT_IMAGE);
    }
    
}
