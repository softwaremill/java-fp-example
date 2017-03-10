package com.softwaremill.java_fp_example;

import javaslang.collection.List;
import javaslang.control.Try;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.net.URL;

import static com.softwaremill.java_fp_example.DefaultImage.DEFAULT_IMAGE;

@Slf4j
public class FacebookImageVersion2Javaslang {

    private final static String FACEBOOK_IMAGE_TAG = "og:image";
    private final static int TEN_SECONDS = 10_000;

    private final String url;

    public FacebookImageVersion2Javaslang(String pageUrl) {
        Try<String> imageTry = Try.of(() -> {
            Document document = Jsoup.parse(new URL(pageUrl), TEN_SECONDS);
            List<Element> ogImages = List.ofAll(document.head().getElementsByTag("meta"))
                    .filter(e -> FACEBOOK_IMAGE_TAG.equals(e.attr("property")));
            if (ogImages.isEmpty()) {
                log.warn("No {} found for blog post {}", FACEBOOK_IMAGE_TAG, pageUrl);
                return DEFAULT_IMAGE;
            } else {
                return ogImages.get(0).attr("content");    
            }
        });

        url = imageTry
            .onFailure(error -> log.error("Unable to extract og:image from url {}. Problem: {}", pageUrl, error.getMessage()))
            .getOrElse(DEFAULT_IMAGE);
    } 

    public String getUrl() {
        return url;
    }
    
}
