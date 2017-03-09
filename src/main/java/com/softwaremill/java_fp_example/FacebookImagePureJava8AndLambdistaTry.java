package com.softwaremill.java_fp_example;

import com.lambdista.util.Try;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;

import java.io.IOException;
import java.net.URL;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static com.softwaremill.java_fp_example.DefaultImageEnum.DEFAULT_IMAGE_URL;

@Slf4j
public class FacebookImagePureJava8AndLambdistaTry {

    private final static String FACEBOOK_IMAGE_TAG = "og:image";
    private final static int TEN_SECONDS = 10_000;

    private static Document downloadAndParse(String pageUrl) throws IOException {
        return Jsoup.parse(new URL(pageUrl), TEN_SECONDS);
    }

    private static final Function<Document, Stream<Element>> findMetaElements = doc ->
            doc.head().getElementsByTag("property").stream();

    private static final Predicate<Element> withFacebookImage = el ->
            "og:image".equals(el.attr("property"));

    private static final Function<Element, Supplier<String>> getImageUrl = e ->
            () -> e.attr("content");

    public String extractImageAddressFrom(String pageUrl) {
        Try<String> recover = Try.apply(() -> downloadAndParse(pageUrl))
                .map(findMetaElements)
                .map(elements -> elements
                        .filter(withFacebookImage)
                        .findFirst()
                        .map(getImageUrl)
                        .orElse(() -> DEFAULT_IMAGE_URL.getBecause(log ->
                                log.warn("No {} found for blog post {}", FACEBOOK_IMAGE_TAG, pageUrl))))
                .recover(error -> DEFAULT_IMAGE_URL.getBecause(log ->
                        log.error("Unable to extract og:image from url {}. Problem: {}", pageUrl, error.getMessage())));
        return recover.get();
    }
    
}

@Slf4j
enum DefaultImageEnum {

    DEFAULT_IMAGE_URL;

    String getBecause(Consumer<Logger> c) {
        c.accept(log);
        return "https://softwaremill.com/images/logo-vertical.023d8496.png";
    }
    
}


