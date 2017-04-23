package com.softwaremill.java_fp_example.contest.adamsiemion

import spock.lang.Specification

class WebPageTest extends Specification {

    def "should return an empty list of images when a document without head is provided"() {
        given:
        def document = new DocumentStub().build()
        def webPage = new WebPage(document)

        when:
        def images = webPage.openGraphHeadImages()

        then:
        images.empty
    }

    def "should return an empty list of images when a document with an empty head is provided"() {
        given:
        def document = new DocumentStub().withHead().build()
        def webPage = new WebPage(document)

        when:
        def images = webPage.openGraphHeadImages()

        then:
        images.empty
    }

    def "should return a list with 2 images when a document with 2 image meta properties is provided"() {
        given:
        def document = new DocumentStub().withMetaPropertyImage('1').withMetaPropertyImage('2').build()
        def webPage = new WebPage(document)

        when:
        def images = webPage.openGraphHeadImages()

        then:
        images*.content() == ['1', '2']
    }
}
