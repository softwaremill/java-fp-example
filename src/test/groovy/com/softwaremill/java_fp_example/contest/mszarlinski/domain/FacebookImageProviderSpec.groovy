package com.softwaremill.java_fp_example.contest.mszarlinski.domain

import com.softwaremill.java_fp_example.contest.mszarlinski.adapter.jsoup.JsoupWebPageLoader
import spock.lang.Specification
import spock.lang.Unroll

import java.time.Duration

import static com.softwaremill.java_fp_example.DefaultImage.DEFAULT_IMAGE

class FacebookImageProviderSpec extends Specification {

    @Unroll
    def "should test Provider version with address #postAddress"() {
        given:
            FacebookImageProvider provider = new FacebookImageProvider(
                    new JsoupWebPageLoader(Duration.ofSeconds(10)))

        when:
            def image = provider.getImageUrlFromPage(postAddress)

        then:
            image.url == expectedImageUrl

        where:
            postAddress                                                || expectedImageUrl
            "https://softwaremill.com/the-wrong-abstraction-recap/"    || "https://softwaremill.com/images/uploads/2017/02/street-shoe-chewing-gum.0526d557.jpg"
            "https://softwaremill.com/using-kafka-as-a-message-queue/" || "https://softwaremill.com/images/uploads/2017/02/kmq.93f842cf.png"
            "https://twitter.com/softwaremill"                         || DEFAULT_IMAGE
            "http://i-do-not-exist.pl"                                 || DEFAULT_IMAGE
    }
}
