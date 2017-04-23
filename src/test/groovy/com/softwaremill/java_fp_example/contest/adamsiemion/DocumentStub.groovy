package com.softwaremill.java_fp_example.contest.adamsiemion

import org.jsoup.Jsoup

class DocumentStub {
    def withHead
    def properties = []

    def withMetaPropertyImage(String value) {
        withHead = true
        properties << "<meta property=\"og:image\" content=\"$value\">"
        this
    }

    def withHead() {
        withHead = true
        this
    }

    def build() {
        String html = "<html>"
        if (withHead)
            html += "<head>"
        html += properties.join()
        if (withHead)
            html += "</head>"
        html += "</html>"
        Jsoup.parse(html)
    }

}
