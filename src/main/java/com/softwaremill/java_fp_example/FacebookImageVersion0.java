package com.softwaremill.java_fp_example;

import javaslang.collection.List;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.net.URL;

import static com.softwaremill.java_fp_example.DefaultImage.DEFAULT_IMAGE;

@Slf4j
public class FacebookImageVersion0 {

    private final static String FACEBOOK_IMAGE_TAG = "og:image";
    private final static int TEN_SECONDS = 10_000;
    
    public static String extractImageAddressFrom(String pageUrl) {
        Document document;
        try {
            document = Jsoup.parse(new URL(pageUrl), TEN_SECONDS);
        } catch (IOException e) {
            log.error("Unable to extract og:image from url {}. Problem: {}", pageUrl, e.getMessage());
            return DEFAULT_IMAGE;
        }
        List<Element> ogImages = List
                .ofAll(document.head().getElementsByTag("meta"))
                .filter(e -> FACEBOOK_IMAGE_TAG.equals(e.attr("property")));
        if (ogImages.isEmpty()) {
            log.warn("No {} found for blog post {}", FACEBOOK_IMAGE_TAG, pageUrl);
            return DEFAULT_IMAGE;
        }
        return ogImages.get(0).attr("content");
    }
    
}
