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

    private static final Function<Element, String> toContentAttribute = element -> element.attr("content");

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
        return parseContentFrom(url)
                .onFailure(logErrorFor(url))
                .map(this::toFacebookImageTags)
                .getOrElse(Stream::empty);
    }

    private Try<Document> parseContentFrom(String url) {
        return Try.of(() -> connect(url)
                .timeout(TEN_SECONDS)
                .get());
    }

    private Stream<Element> toFacebookImageTags(Document document) {
        return document
                .head()
                .select("meta[property=" + FACEBOOK_IMAGE_TAG + ']')
                .stream();
    }

    private Consumer<Throwable> logErrorFor(String url) {
        return exception -> log.error("Unable to extract og:image from url {}. Problem: {}", url, exception.getMessage());
    }

}
