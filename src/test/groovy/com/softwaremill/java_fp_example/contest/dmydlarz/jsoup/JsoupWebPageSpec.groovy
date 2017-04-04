package com.softwaremill.java_fp_example.contest.dmydlarz.jsoup

import com.softwaremill.java_fp_example.contest.dmydlarz.WebPage
import spock.lang.Specification
import spock.lang.Unroll

class JsoupWebPageSpec extends Specification {
    static final int TIMEOUT_MS = 10_000
    static final String DEFAULT_IMAGE = "https://softwaremill.com/images/logo-vertical.023d8496.png"

    @Unroll
    def "it grabs facebook image for: #uri"() {
        when:
            WebPage webPage = new JsoupWebPage(uri)

        then:
            webPage.facebookImage() == expectedImageUrl

        where:
            uri                                                                                 || expectedImageUrl
            new RemoteJsoupDocument("https://softwaremill.com/the-wrong-abstraction-recap/")    || "https://softwaremill.com/images/uploads/2017/02/street-shoe-chewing-gum.0526d557.jpg"
            new RemoteJsoupDocument("https://softwaremill.com/using-kafka-as-a-message-queue/") || "https://softwaremill.com/images/uploads/2017/02/kmq.93f842cf.png"
            new JsoupDocument.Fake("fake-og:image-uri")                                         || "fake-og:image-uri"
    }

    @Unroll
    def "it returns default image with Safe wrapper for: #document"() {
        when:
            WebPage webPage = new WebPage.Safe(new JsoupWebPage(document), DEFAULT_IMAGE)

        then:
            webPage.facebookImage() == DEFAULT_IMAGE

        where:
            document << [
                    new RemoteJsoupDocument("https://twitter.com/softwaremill"),
                    new RemoteJsoupDocument("http://i-do-not-exist.pl"),
                    new JsoupDocument.Empty()
            ]
    }
}
