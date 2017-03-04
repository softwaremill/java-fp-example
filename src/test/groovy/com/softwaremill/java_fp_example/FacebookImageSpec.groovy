package com.softwaremill.java_fp_example

import spock.lang.Specification
import spock.lang.Unroll

import static com.softwaremill.java_fp_example.FacebookImage.DEFAULT_IMAGE


class FacebookImageSpec extends Specification {

    @Unroll
    def "should test Initial Version with address #postAddress"() {
        given:
        FacebookImage facebookImage = new FacebookImageInitialVersion()

        when:
        String imageAddress = facebookImage.extractImageAddressFrom(postAddress)

        then:
        imageAddress == expectedImageUrl

        where:
        postAddress                                                || expectedImageUrl
        "https://softwaremill.com/the-wrong-abstraction-recap/"    || "https://softwaremill.com/images/team-members/chmielarz.6e3a0d1d.jpg"
        "https://softwaremill.com/using-kafka-as-a-message-queue/" || "https://softwaremill.com/images/team-members/warski.dd6c745b.jpg"
        "https://twitter.com/softwaremill"                         || DEFAULT_IMAGE
        "http://i-do-not-exist.pl"                                 || DEFAULT_IMAGE
    }

    @Unroll
    def "should test Javaslang Version One with address #postAddress"() {
        given:
        FacebookImage facebookImage = new FacebookImageJavaslangVersionOne()

        when:
        String imageAddress = facebookImage.extractImageAddressFrom(postAddress)

        then:
        imageAddress == expectedImageUrl

        where:
        postAddress                                                || expectedImageUrl
        "https://softwaremill.com/the-wrong-abstraction-recap/"    || "https://softwaremill.com/images/team-members/chmielarz.6e3a0d1d.jpg"
        "https://softwaremill.com/using-kafka-as-a-message-queue/" || "https://softwaremill.com/images/team-members/warski.dd6c745b.jpg"
        "https://twitter.com/softwaremill"                         || DEFAULT_IMAGE
        "http://i-do-not-exist.pl"                                 || DEFAULT_IMAGE
    }

    @Unroll
    def "should test Javaslang Version Two with address #postAddress"() {
        given:
        FacebookImage facebookImage = new FacebookImageJavaslangVersionTwo()

        when:
        String imageAddress = facebookImage.extractImageAddressFrom(postAddress)

        then:
        imageAddress == expectedImageUrl

        where:
        postAddress                                                || expectedImageUrl
        "https://softwaremill.com/the-wrong-abstraction-recap/"    || "https://softwaremill.com/images/team-members/chmielarz.6e3a0d1d.jpg"
        "https://softwaremill.com/using-kafka-as-a-message-queue/" || "https://softwaremill.com/images/team-members/warski.dd6c745b.jpg"
        "https://twitter.com/softwaremill"                         || DEFAULT_IMAGE
        "http://i-do-not-exist.pl"                                 || DEFAULT_IMAGE
    }
    
    
    
}
