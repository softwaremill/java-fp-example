package com.softwaremill.java_fp_example.contest.aplotnikov;

import javaslang.control.Try;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Stream;

import static com.softwaremill.java_fp_example.DefaultImage.DEFAULT_IMAGE;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.jsoup.Jsoup.connect;

@Slf4j
public class FacebookImageAddressExtractor {
    private static final String FACEBOOK_IMAGE_TAG = "og:image";

    private static final int TEN_SECONDS = (int) SECONDS.toMillis(10);

    private static final Function<Element, String> toContentAttribute = FacebookImageAddressExtractor::getContentAttributeFrom;

    private static final Function<Document, Stream<Element>> toFacebookImageTags = FacebookImageAddressExtractor::findFacebookImageTags;

    public String extractFrom(String url) {
        return extractFacebookImageTagsFrom(url)
                .findFirst()
                .map(toContentAttribute)
                .orElseGet(() -> {
                    log.warn("No {} found for blog post {}", FACEBOOK_IMAGE_TAG, url);
                    return DEFAULT_IMAGE;
                });
    }

    private Stream<Element> extractFacebookImageTagsFrom(String url) {
        return parseContentFrom(url).map(toFacebookImageTags).getOrElse(Stream::empty);
    }

    private static Stream<Element> findFacebookImageTags(Document document) {
        return document.head().select("meta[property=" + FACEBOOK_IMAGE_TAG + ']').stream();
    }

    private static String getContentAttributeFrom(Element element) {
        return element.attr("content");
    }

    private Try<Document> parseContentFrom(String url) {
        return Try.of(() -> connect(url).timeout(TEN_SECONDS).get()).onFailure(logErrorFor(url));
    }

    private Consumer<Throwable> logErrorFor(String url) {
        return exception -> log.error("Unable to extract og:image from url {}. Problem: {}", url, exception.getMessage());
    }
}
