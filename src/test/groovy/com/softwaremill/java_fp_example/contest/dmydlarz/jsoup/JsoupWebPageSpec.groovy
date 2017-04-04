package com.softwaremill.java_fp_example.contest.dmydlarz.jsoup

import com.softwaremill.java_fp_example.contest.dmydlarz.WebPage
import spock.lang.Specification
import spock.lang.Unroll

class JsoupWebPageSpec extends Specification {
    static final int TIMEOUT_MS = 10_000
    static final String DEFAULT_IMAGE = "https://softwaremill.com/images/logo-vertical.023d8496.png"

    @Unroll
    def "it grabs facebook image for: #document"() {
        when:
            WebPage webPage = new JsoupWebPage(document)

        then:
            webPage.facebookImageOrElse(DEFAULT_IMAGE) == expectedImageUrl

        where:
            document                                                                                  || expectedImageUrl
            new JsoupDocument("https://softwaremill.com/the-wrong-abstraction-recap/", TIMEOUT_MS)    || "https://softwaremill.com/images/uploads/2017/02/street-shoe-chewing-gum.0526d557.jpg"
            new JsoupDocument("https://softwaremill.com/using-kafka-as-a-message-queue/", TIMEOUT_MS) || "https://softwaremill.com/images/uploads/2017/02/kmq.93f842cf.png"
            new Document.Fake("fake-og:image-uri")                                                    || "fake-og:image-uri"
    }

    @Unroll
    def "it returns default image for: #document"() {
        when:
            WebPage webPage = new JsoupWebPage(document)

        then:
            webPage.facebookImageOrElse(DEFAULT_IMAGE) == DEFAULT_IMAGE

        where:
            document << [
                    new JsoupDocument("https://twitter.com/softwaremill", TIMEOUT_MS),
                    new JsoupDocument("http://i-do-not-exist.pl", TIMEOUT_MS),
                    new Document.Empty()
            ]
    }
}
