package com.softwaremill.java_fp_example.contest.dmydlarz.jsoup;

import com.softwaremill.java_fp_example.contest.dmydlarz.WebPage;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class JsoupWebPage implements WebPage {
    private final Document document;

    public JsoupWebPage(Document document) {
        this.document = document;
    }

    public String facebookImage() throws Exception {
        try {
            return new JsoupFacebookImage(document).uri();
        } catch (Exception e) {
            throw new Exception(String.format("Can't get facebook image for %s", document), e);
        }
    }
}
