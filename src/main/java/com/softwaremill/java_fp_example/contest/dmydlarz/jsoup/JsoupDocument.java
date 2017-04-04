package com.softwaremill.java_fp_example.contest.dmydlarz.jsoup;

import lombok.ToString;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

@ToString(of = "uri")
class JsoupDocument implements Document {
    private final String uri;
    private final int timeout;

    JsoupDocument(String uri, int timeout) {
        this.uri = uri;
        this.timeout = timeout;
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
}
