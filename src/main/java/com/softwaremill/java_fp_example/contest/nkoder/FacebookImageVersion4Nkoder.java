package com.softwaremill.java_fp_example.contest.nkoder;

import com.softwaremill.java_fp_example.contest.nkoder.html.HtmlDocument;
import com.softwaremill.java_fp_example.contest.nkoder.html.HtmlMetaTag;
import javaslang.collection.Stream;
import javaslang.control.Option;
import javaslang.control.Try;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.net.URL;

import static com.softwaremill.java_fp_example.DefaultImage.DEFAULT_IMAGE;

@Slf4j
public class FacebookImageVersion4Nkoder {

    private final static int TEN_SECONDS = 10_000;
    private final static String FACEBOOK_IMAGE_TAG = "og:image";

    private final String pageUrl;

    public static FacebookImageVersion4Nkoder fromPage(String pageUrl) {
        return new FacebookImageVersion4Nkoder(pageUrl);
    }

    private FacebookImageVersion4Nkoder(String pageUrl) {
        this.pageUrl = pageUrl;
    }

    public String url() {
        return firstFacebookImageUrlFrom(metaTagsOfPage(pageUrl)).getOrElse(() -> {
            logLackOfFacebookImage(pageUrl);
            return DEFAULT_IMAGE;
        });
    }

    private Stream<HtmlMetaTag> metaTagsOfPage(String pageUrl) {
        return Try.of(() -> parsePage(pageUrl))
                .onFailure(throwable -> logParsingError(this.pageUrl, throwable))
                .toStream()
                .flatMap(document -> document.metaTags());
    }

    private Option<String> firstFacebookImageUrlFrom(Stream<HtmlMetaTag> metaTags) {
        return metaTags
                .filter(metaTag -> metaTag.hasProperty(FACEBOOK_IMAGE_TAG))
                .map(metaTag -> metaTag.content())
                .headOption();
    }

    private HtmlDocument parsePage(String urlOfPageToParse) throws IOException {
        Document document = Jsoup.parse(new URL(urlOfPageToParse), TEN_SECONDS);
        return new HtmlDocument(document);
    }

    private void logParsingError(String urlOfParsedPage, Throwable cause) {
        log.error(
                "Unable to extract og:image from url {}. Problem: {}",
                urlOfParsedPage,
                cause.getMessage()
        );
    }

    private void logLackOfFacebookImage(String urlOfParsedPage) {
        log.warn(
                "No {} found for blog post {}",
                FACEBOOK_IMAGE_TAG,
                urlOfParsedPage
        );
    }
}
