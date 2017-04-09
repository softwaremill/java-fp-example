package com.softwaremill.java_fp_example.contest.mszarlinski.domain;

import com.softwaremill.java_fp_example.contest.mszarlinski.domain.web.Element;
import com.softwaremill.java_fp_example.contest.mszarlinski.domain.web.Elements;
import com.softwaremill.java_fp_example.contest.mszarlinski.domain.web.WebPage;
import lombok.extern.slf4j.Slf4j;

import java.util.function.Consumer;
import java.util.function.Function;

import static java.lang.String.format;

/**
 * Component responsible for extracting FB image from given page.
 */
@Slf4j
public class FacebookImageProvider {

    private final WebPageLoader webPageLoader;

    public FacebookImageProvider(WebPageLoader webPageLoader) {
        this.webPageLoader = webPageLoader;
    }

    public FacebookImage getImageUrlFromPage(String pageUrl) {
        return webPageLoader.tryLoadWebPage(pageUrl)
                .map(WebPage::head)
                .map(metasWithImage())
                .map(Elements::first)
                .map(fbImage())
                .onFailure(logError(pageUrl))
                .getOrElse(FacebookImage.DEFAULT);
    }

    private Function<Element, Elements> metasWithImage() {
        return h -> h.childrenByTag("meta")
                .filterByAttribute("property", FacebookImage.FACEBOOK_IMAGE_TAG);
    }

    private Function<Element, FacebookImage> fbImage() {
        return e -> e.attr("content")
                .map(FacebookImage::new)
                .getOrElse(FacebookImage.DEFAULT);
    }

    private Consumer<Throwable> logError(String pageUrl) {
        return t -> log.warn(format("Failed to extract image from %s", pageUrl), t);
    }
}
