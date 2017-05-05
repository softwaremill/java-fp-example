package com.softwaremill.java_fp_example.contest.wkromolicki;

import static com.softwaremill.java_fp_example.DefaultImage.DEFAULT_IMAGE;
import static com.softwaremill.java_fp_example.contest.wkromolicki.ParserFunctions.*;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

import org.jsoup.nodes.Element;

import javaslang.control.Try;

public class FacebookImage {

    private final static Predicate<Element> FACEBOOK_IMAGE_TAG_FILTER = el -> "og:image".equals(el.attr("property"));

    public static String extractImage(String pageUrl) {
        return findImage.apply(FACEBOOK_IMAGE_TAG_FILTER)
                .apply(pageUrl)
                .onFailure(handleExceptionFor(pageUrl))
                .getOrElse(DEFAULT_IMAGE);
    }

    private static Function<Predicate<Element>, Function<String, Try<String>>> findImage =
       filter -> fetchUrl.andThen(lift(parseUrl)).andThen(lift(findMetaTag.apply(filter))).andThen(lift(extractContent));


    private static Consumer<Throwable> handleExceptionFor(String pageUrl) {
        return e -> log(String.format("Unable to extract og:image from url %s. Problem: %s", pageUrl, e.getMessage()));
    }

    private static void log(String message) {
        System.out.println(message);
    }
}
