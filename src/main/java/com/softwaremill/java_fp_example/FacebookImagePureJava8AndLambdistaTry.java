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

import static com.softwaremill.java_fp_example.FacebookImagePureJava8AndLambdistaTry.DefaultImage.DEFAULT_IMAGE_VALUE;

@Slf4j
public class FacebookImagePureJava8AndLambdistaTry implements FacebookImage {

    private static Document downloadAndParse(String pageUrl) throws IOException {
        return Jsoup.parse(new URL(pageUrl), TEN_SECONDS);
    }

    private static final Function<Document, Stream<Element>> findMetaElements = doc ->
            doc.head().getElementsByTag("property").stream();

    private static final Predicate<Element> withFacebookImage = el ->
            "og:image".equals(el.attr("property"));

    private static final Function<Element, Supplier<String>> getImageUrl = e ->
            () -> e.attr("content");

    @Override
    public String extractImageAddressFrom(String pageUrl) {
        return Try
            .apply(() -> downloadAndParse(pageUrl))
            .map(findMetaElements)
            .map(elements -> elements
                    .filter(withFacebookImage)
                    .findFirst()
                    .map(getImageUrl)
                    .orElse(() -> DEFAULT_IMAGE_VALUE.getBecause(log ->
                            log.warn("No {} found for blog post {}", FACEBOOK_IMAGE_TAG, pageUrl))))
            .recover(error -> DEFAULT_IMAGE_VALUE.getBecause(log ->
                    log.error("Unable to extract og:image from url {}. Problem: {}", pageUrl, error.getMessage())))
            .get();
    }

    public enum DefaultImage {

        DEFAULT_IMAGE_VALUE;

        String getBecause(Consumer<Logger> c) {
            c.accept(log);
            return DEFAULT_IMAGE;
        }
    }
}


