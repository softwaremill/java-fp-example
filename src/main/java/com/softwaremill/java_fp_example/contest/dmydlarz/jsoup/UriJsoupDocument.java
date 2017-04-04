package com.softwaremill.java_fp_example.contest.dmydlarz.jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

class UriJsoupDocument implements JsoupDocument {
    private static final int DEFAULT_TIMEOUT = 10_000;
    private final String uri;
    private final int timeout;

    UriJsoupDocument(String uri, int timeout) {
        this.uri = uri;
        this.timeout = timeout;
    }

    UriJsoupDocument(String uri) {
        this(uri, DEFAULT_TIMEOUT);
    }

    @Override
    public Element head() throws Exception {
        try {
            return Jsoup.parse(new URL(uri), timeout).head();
        } catch (MalformedURLException e) {
            throw new Exception(String.format("Invalid uri passed: %s", uri), e);
        } catch (IOException e) {
            throw new Exception(String.format("Unable to connect to: %s", uri), e);
        }
    }

    @Override
    public String toString() {
        return uri;
    }
}
