package com.softwaremill.java_fp_example.contest.dmydlarz.jsoup;

import com.softwaremill.java_fp_example.contest.dmydlarz.FacebookImage;
import com.softwaremill.java_fp_example.contest.dmydlarz.functional.Filtered;
import com.softwaremill.java_fp_example.contest.dmydlarz.functional.First;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@EqualsAndHashCode
final class JsoupFacebookImage implements FacebookImage {
    private final JsoupElement ogImage;

    JsoupFacebookImage(Document document) {
        this.ogImage = new JsoupElement(new First<>(new Filtered<>(
                new JsoupTagElements(document, "meta"),
                new JsoupPropertyMask("og:image")
        )));
    }

    @Override
    public String uri() throws Exception {
        try {
            return ogImage.content();
        } catch (Exception e) {
            throw new Exception(String.format("Can't get og:image content. %s", e.getMessage()), e);
        }
    }
}
