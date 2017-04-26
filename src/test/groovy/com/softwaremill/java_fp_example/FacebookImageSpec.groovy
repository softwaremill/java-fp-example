package com.softwaremill.java_fp_example

import spock.lang.Specification
import spock.lang.Unroll

import static com.softwaremill.java_fp_example.DefaultImage.DEFAULT_IMAGE
import com.softwaremill.java_fp_example.contest.ivanopagano.FacebookImage


class FacebookImageSpec extends Specification {

    @Unroll
    def "should test Initial Version with address #postAddress"() {
        given:
        FacebookImageVersion0 facebookImage = new FacebookImageVersion0()

        when:
        String imageAddress = facebookImage.extractImageAddressFrom(postAddress)

        then:
        imageAddress == expectedImageUrl

        where:
        postAddress                                                || expectedImageUrl
        "https://softwaremill.com/the-wrong-abstraction-recap/"    || "https://softwaremill.com/images/uploads/2017/02/street-shoe-chewing-gum.0526d557.jpg"
        "https://softwaremill.com/using-kafka-as-a-message-queue/" || "https://softwaremill.com/images/uploads/2017/02/kmq.93f842cf.png"
        "https://twitter.com/softwaremill"                         || DEFAULT_IMAGE
        "http://i-do-not-exist.pl"                                 || DEFAULT_IMAGE
    }

    @Unroll
    def "should test More Object Oriented version with address #postAddress"() {
        when:
        FacebookImageVersion1MoreObjectOriented facebookImage = new FacebookImageVersion1MoreObjectOriented(postAddress)

        then:
        facebookImage.getUrl() == expectedImageUrl

        where:
        postAddress                                                || expectedImageUrl
        "https://softwaremill.com/the-wrong-abstraction-recap/"    || "https://softwaremill.com/images/uploads/2017/02/street-shoe-chewing-gum.0526d557.jpg"
        "https://softwaremill.com/using-kafka-as-a-message-queue/" || "https://softwaremill.com/images/uploads/2017/02/kmq.93f842cf.png"
        "https://twitter.com/softwaremill"                         || DEFAULT_IMAGE
        "http://i-do-not-exist.pl"                                 || DEFAULT_IMAGE
    }

    @Unroll
    def "should test Javaslang version with address #postAddress"() {
        when:
        FacebookImageVersion2Javaslang facebookImage = new FacebookImageVersion2Javaslang(postAddress)

        then:
        facebookImage.getUrl() == expectedImageUrl

        where:
        postAddress                                                || expectedImageUrl
        "https://softwaremill.com/the-wrong-abstraction-recap/"    || "https://softwaremill.com/images/uploads/2017/02/street-shoe-chewing-gum.0526d557.jpg"
        "https://softwaremill.com/using-kafka-as-a-message-queue/" || "https://softwaremill.com/images/uploads/2017/02/kmq.93f842cf.png"
        "https://twitter.com/softwaremill"                         || DEFAULT_IMAGE
        "http://i-do-not-exist.pl"                                 || DEFAULT_IMAGE
    }

    @Unroll
    def "should test Better Javaslang version with address #postAddress"() {
        when:
        FacebookImageVersion2Javaslang facebookImage = new FacebookImageVersion2Javaslang(postAddress)

        then:
        facebookImage.getUrl() == expectedImageUrl

        where:
        postAddress                                                || expectedImageUrl
        "https://softwaremill.com/the-wrong-abstraction-recap/"    || "https://softwaremill.com/images/uploads/2017/02/street-shoe-chewing-gum.0526d557.jpg"
        "https://softwaremill.com/using-kafka-as-a-message-queue/" || "https://softwaremill.com/images/uploads/2017/02/kmq.93f842cf.png"
        "https://twitter.com/softwaremill"                         || DEFAULT_IMAGE
        "http://i-do-not-exist.pl"                                 || DEFAULT_IMAGE
    }

    @Unroll
    def "should test ivanopagano Javaslang version with address #postAddress"() {
        when:
        FacebookImage facebookImage = new FacebookImage(postAddress)

        then:
        facebookImage.getUrl() == expectedImageUrl

        where:
        postAddress                                                || expectedImageUrl
        "https://softwaremill.com/the-wrong-abstraction-recap/"    || "https://softwaremill.com/images/uploads/2017/02/street-shoe-chewing-gum.0526d557.jpg"
        "https://softwaremill.com/using-kafka-as-a-message-queue/" || "https://softwaremill.com/images/uploads/2017/02/kmq.93f842cf.png"
        "https://twitter.com/softwaremill"                         || DEFAULT_IMAGE
        "http://i-do-not-exist.pl"                                 || DEFAULT_IMAGE
    }
    
}
