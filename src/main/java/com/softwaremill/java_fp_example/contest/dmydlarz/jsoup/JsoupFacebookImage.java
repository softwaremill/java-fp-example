package com.softwaremill.java_fp_example.contest.dmydlarz.jsoup;

import com.softwaremill.java_fp_example.contest.dmydlarz.functional.Filtered;
import com.softwaremill.java_fp_example.contest.dmydlarz.functional.First;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@EqualsAndHashCode
final class JsoupFacebookImage {
    private final JsoupElement ogImage;

    JsoupFacebookImage(JsoupDocument jsoupDocument) {
        this.ogImage = new JsoupElement(new First<>(new Filtered<>(
                new JsoupTagElements(jsoupDocument, "meta"),
                new JsoupPropertyMask("og:image")
        )));
    }

    String uri() throws Exception {
        try {
            return ogImage.content();
        } catch (Exception e) {
            throw new Exception(String.format("Can't get og:image. %s", e.getMessage()), e);
        }
    }
}
