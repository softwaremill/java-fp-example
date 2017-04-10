package com.softwaremill.java_fp_example.contest.wkromolicki;

import java.net.URL;
import java.util.function.Function;
import java.util.function.Predicate;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import javaslang.collection.List;
import javaslang.control.Try;

public class ParserFunctions {
    public final static int TIMEOUT = 10_000;

    public static Function<String, Try<Document>> parser = url -> Try.of(() -> Jsoup.parse(new URL(url), TIMEOUT));

    public static Function<Document, Try<Element>> metaTagFinder(Predicate<Element> tagFilter) {

        return document -> List.ofAll(document.head().getElementsByTag("meta")).filter(tagFilter).headOption().toTry();
    }

    public static Function<Element, Try<String>> contentExtractor = el -> Try.of(() -> el.attr("content"));

    public static <A,B> Function<Try<A>, Try<B>> composeM(Function<A, Try<B>> composer) {
        return in -> in.flatMap(composer::apply);
    }
}
