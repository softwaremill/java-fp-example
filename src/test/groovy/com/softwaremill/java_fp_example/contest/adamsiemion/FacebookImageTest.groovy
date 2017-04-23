package com.softwaremill.java_fp_example.contest.adamsiemion

import com.softwaremill.java_fp_example.DefaultImage
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings
import spock.lang.Specification

class FacebookImageTest extends Specification {

    def "should extract the image address when web page contains one image address"() {
        given:
        def document = new DocumentStub().withMetaPropertyImage('1').build()
        WebPageAddress webPageAddress = Mock()
        webPageAddress.download() >> new WebPage(document)
        def facebookImage = new FacebookImage(webPageAddress)

        when:
        def imageAddress = facebookImage.extractImageAddress()

        then:
        imageAddress == '1'
    }

    def "should extract the default image address when web page does not contain any image addresses"() {
        given:
        def document = new DocumentStub().build()
        WebPageAddress webPageAddress = Mock()
        webPageAddress.download() >> new WebPage(document)
        def facebookImage = new FacebookImage(webPageAddress)

        when:
        def imageAddress = facebookImage.extractImageAddress()

        then:
        imageAddress == DefaultImage.DEFAULT_IMAGE
    }

    @SuppressFBWarnings("SE_NO_SERIALVERSIONID")
    def "should extract the default image address when downloading of the web page fails"() {
        given:
        WebPageAddress webPageAddress = Mock()
        webPageAddress.download() >> {
            throw new WebPageAddress.WebPageDownloadException("download failed", new IOException())
        }
        def facebookImage = new FacebookImage(webPageAddress)

        when:
        def imageAddress = facebookImage.extractImageAddress()

        then:
        imageAddress == DefaultImage.DEFAULT_IMAGE
    }
}
