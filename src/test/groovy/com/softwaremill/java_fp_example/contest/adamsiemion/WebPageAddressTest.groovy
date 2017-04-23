package com.softwaremill.java_fp_example.contest.adamsiemion

import spock.lang.Specification

import static com.softwaremill.java_fp_example.contest.adamsiemion.WebPageAddress.*

class WebPageAddressTest extends Specification {

    def "should throw WebPageDownloadException when an incorrect url is provided"() {
        given:
        WebPageAddress webPageAddress = new WebPageAddress("wrong_url")

        when:
        webPageAddress.download()

        then:
        def e = thrown(WebPageDownloadException)
        e.message == "Could not download page wrong_url"
    }
}
