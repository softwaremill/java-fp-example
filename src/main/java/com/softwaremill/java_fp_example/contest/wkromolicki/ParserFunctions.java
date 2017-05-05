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

    public static Function<Predicate<Element>, Function<Document, Try<Element>>> findMetaTag =
            tagFilter -> document -> List.ofAll(document.head().getElementsByTag("meta")).filter(tagFilter).headOption().toTry();


    public static Function<Element, Try<String>> extractContent = safe(el -> el.attr("content"));

    public static Function<String, Try<URL>> fetchUrl = safe(URL::new);

    public static Function<URL, Try<Document>> parseUrl = safe(url -> Jsoup.parse(url, TIMEOUT));


    //below are helper methods which do not belong to parser domain and should be in some library
    public static <A,B> Function<Try<A>, Try<B>> lift(Function<A, Try<B>> func) {
        return in -> in.flatMap(func::apply);
    }

    public static <A, B> Function<A, Try<B>> safe(Try.CheckedFunction<A, B> func) {
        return a -> Try.of(() -> func.apply(a));
    }


}
