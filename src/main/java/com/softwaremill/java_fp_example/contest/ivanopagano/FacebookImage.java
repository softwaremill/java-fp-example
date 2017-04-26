package com.softwaremill.java_fp_example.contest.ivanopagano;

import javaslang.collection.List;
import javaslang.control.Option;
import javaslang.concurrent.Future;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Element;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

import static com.softwaremill.java_fp_example.DefaultImage.DEFAULT_IMAGE;
import static com.softwaremill.java_fp_example.contest.ivanopagano.PageParsing.*;

@Slf4j
public class FacebookImage {

    private final static String FACEBOOK_IMAGE_TAG = "og:image";

    public final Future<String> url;

    private final static Predicate<Element> facebookImages = elementsWithProperty.apply(FACEBOOK_IMAGE_TAG);

    private final static Function<List<Element>, Option<Element>> firstImage = es -> es.filter(facebookImages).peekOption();

    private static <T> Consumer<T> headerNotFound(final String url) { 
        return t -> log.warn("No meta header {} was found in the page at {}", FACEBOOK_IMAGE_TAG, url);
    }

    private final static Function<String, Future<Option<Element>>> imageHeaderFromDocument = parseUrl
        .andThen(futureLift(PageParsing::extractMetaHeaders))
        .andThen(futureLift(firstImage));


    public FacebookImage(final String pageUrl) {
        url = imageHeaderFromDocument.apply(pageUrl)
            .map(opt -> opt.get())
            .onFailure(headerNotFound(pageUrl))
            .map(content)
            .recover(t -> DEFAULT_IMAGE);
    }

    public String getUrl() {
        return url.get();
    }

    
}
