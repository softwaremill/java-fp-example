package com.softwaremill.java_fp_example.contest.dmydlarz.jsoup;

import com.softwaremill.java_fp_example.contest.dmydlarz.WebPage;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class JsoupWebPage implements WebPage {
    private final JsoupFacebookImage jsoupFacebookImage;

    public JsoupWebPage(String uri, int timeout) {
        jsoupFacebookImage = new JsoupFacebookImage(new JsoupDocument(uri, timeout));
    }

    @Override
    public String facebookImageOrElse(String fallbackUri) {
        String facebookImage = fallbackUri;
        try {
            facebookImage = jsoupFacebookImage.uri();
        } catch (Exception e) {
            log.error("Unable serve Open Graph Image. Problem: {}", e.getMessage());
        }
        return facebookImage;
    }
}
