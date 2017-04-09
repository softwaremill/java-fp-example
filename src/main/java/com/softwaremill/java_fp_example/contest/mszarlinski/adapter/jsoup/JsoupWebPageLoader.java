package com.softwaremill.java_fp_example.contest.mszarlinski.adapter.jsoup;

import com.softwaremill.java_fp_example.contest.mszarlinski.domain.WebPageLoader;
import com.softwaremill.java_fp_example.contest.mszarlinski.domain.web.WebPage;
import javaslang.control.Try;
import org.jsoup.Jsoup;

import java.net.URL;
import java.time.Duration;

class JsoupWebPageLoader implements WebPageLoader {

    private final int webPageLoadingTimeoutMillis;

    JsoupWebPageLoader(Duration webPageLoadingTimeout) {
        this.webPageLoadingTimeoutMillis = (int) webPageLoadingTimeout.toMillis();
    }

    @Override
    public Try<WebPage> tryLoadWebPage(String url) {
        return Try.of(() -> new JsoupWebPage(Jsoup.parse(new URL(url), webPageLoadingTimeoutMillis)));
    }
}
