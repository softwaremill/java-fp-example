package com.softwaremill.java_fp_example.contest.ivanopagano;

import java.net.URL;

import javaslang.collection.List;
import javaslang.concurrent.Future;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.function.Function;
import java.util.function.Predicate;

public class PageParsing {

    private final static int TEN_SECONDS = 10_000;

    public final static Function<String, Predicate<Element>> elementsWithProperty = property -> e -> property.equals(e.attr("property"));
    public final static Function<String, Future<Document>> parseUrl = url -> Future.of(() -> Jsoup.parse(new URL(url), TEN_SECONDS));
    public final static Function<Element, String> content = e -> e.attr("content");
    public static List<Element> extractMetaHeaders(Document doc) {
        return List.ofAll(doc.head().getElementsByTag("meta"));
    }

    public static <A, B> Function<Future<A>, Future<B>> futureLift(Function<A, B> mapper) {
        return fa -> fa.map(mapper);
    }

}