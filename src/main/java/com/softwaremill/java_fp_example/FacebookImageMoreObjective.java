package com.softwaremill.java_fp_example;

import javaslang.collection.List;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.net.URL;

@Slf4j
public class FacebookImageMoreObjective implements FacebookImage2 {
    
    private final String url;

    public FacebookImageMoreObjective(String pageUrl) {
        Document document;
        try {
            document = Jsoup.parse(new URL(pageUrl), TEN_SECONDS);
        } catch (IOException e) {
            log.error("Unable to extract og:image from url {}. Problem: {}", pageUrl, e.getMessage());
            url = DEFAULT_IMAGE;
            return;
        }
        List<Element> ogImages = List
            .ofAll(document.head().getElementsByTag("meta"))
            .filter(e -> FACEBOOK_IMAGE_TAG.equals(e.attr("property")));
        if (ogImages.isEmpty()) {
            log.warn("No {} found for blog post {}", FACEBOOK_IMAGE_TAG, pageUrl);
            url = DEFAULT_IMAGE;
        } else {
            url = ogImages.get(0).attr("content");
        }
        
    }

    @Override
    public String getUrl() {
        return url;
    }
    
}
