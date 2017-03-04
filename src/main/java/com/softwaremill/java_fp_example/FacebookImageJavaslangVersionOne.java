package com.softwaremill.java_fp_example;

import javaslang.collection.List;
import javaslang.control.Try;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.net.URL;

import static javaslang.collection.List.ofAll;

@Slf4j
public class FacebookImageJavaslangVersionOne implements FacebookImage {

    @Override
    public String extractImageAddressFrom(String pageUrl) {
        Try<String> facebookImage = Try.of(() -> {
                Document document = Jsoup.parse(new URL(pageUrl), TEN_SECONDS);
                List<Element> ogImages = ofAll(document.head().getElementsByTag("meta"))
                        .filter(e -> FACEBOOK_IMAGE_TAG.equals(e.attr("property")));
                if (ogImages.isEmpty()) {
                    log.warn("No {} found for blog post {}", FACEBOOK_IMAGE_TAG, pageUrl);
                    return DEFAULT_IMAGE;
                }
                return ogImages.get(0).attr("content");
            }
        );

        return facebookImage
                .onFailure(error -> log.error("Unable to extract og:image from url {}. Problem: {}", pageUrl, error.getMessage()))
                .getOrElse(DEFAULT_IMAGE);
    }
    
}
