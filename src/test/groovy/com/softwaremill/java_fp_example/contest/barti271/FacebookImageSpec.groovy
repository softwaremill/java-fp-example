package com.softwaremill.java_fp_example.contest.barti271

import spock.lang.Specification
import spock.lang.Unroll

import static com.softwaremill.java_fp_example.DefaultImage.DEFAULT_IMAGE

class FacebookImageSpec extends Specification {

    @Unroll
    def "should test Barti271 version with address #postAddress"() {
        when:
        FacebookImageVersionBarti271 facebookImage = new FacebookImageVersionBarti271(postAddress)

        then:
        facebookImage.toString() == expectedImageUrl

        where:
        postAddress                                                || expectedImageUrl
        "https://softwaremill.com/the-wrong-abstraction-recap/"    || "https://softwaremill.com/images/uploads/2017/02/street-shoe-chewing-gum.0526d557.jpg"
        "https://softwaremill.com/using-kafka-as-a-message-queue/" || "https://softwaremill.com/images/uploads/2017/02/kmq.93f842cf.png"
        "https://twitter.com/softwaremill"                         || DEFAULT_IMAGE
        "http://i-do-not-exist.pl"                                 || DEFAULT_IMAGE
    }
}
