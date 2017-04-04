package com.softwaremill.java_fp_example.contest.dmydlarz.jsoup;

import com.softwaremill.java_fp_example.contest.dmydlarz.WebPage;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class JsoupWebPage implements WebPage {
    private final JsoupDocument jsoupDocument;

    public JsoupWebPage(JsoupDocument jsoupDocument) {
        this.jsoupDocument = jsoupDocument;
    }

    public String facebookImage() throws Exception {
        try {
            return new JsoupFacebookImage(jsoupDocument).uri();
        } catch (Exception e) {
            throw new Exception(String.format("Can't get facebook image for %s", jsoupDocument), e);
        }
    }
}
