package com.softwaremill.java_fp_example.contest.barti271.page.content.jspup;

import java.io.IOException;
import java.net.URL;
import java.util.stream.Stream;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;

import com.softwaremill.java_fp_example.contest.barti271.page.content.source.MetaElement;
import com.softwaremill.java_fp_example.contest.barti271.page.content.source.Source;


/**
 * {@link Jsoup} based implementation of {@link Source}.
 */
public class JsoupSource implements Source {

    private static final int TIMEOUT_TEN_SECONDS = 10_000;

    private final String url;

    public JsoupSource(String url) {
        this.url = url;
    }

    @Override
    public Stream<MetaElement> getMetaElements() {
        Element head = parseHead();
        return head == null
            ? Stream.empty()
            : head.getElementsByTag("meta").stream().map(JsoupMetaElement::new);
    }

    private Element parseHead() {
        try {
            return tryParseHead();
        } catch (IOException e) {
            throw new IllegalStateException(url, e);
        }
    }

    private Element tryParseHead() throws IOException {
        return Jsoup.parse(new URL(url), TIMEOUT_TEN_SECONDS).head();
    }

    @Override
    public String toString() {
        return url;
    }
}
