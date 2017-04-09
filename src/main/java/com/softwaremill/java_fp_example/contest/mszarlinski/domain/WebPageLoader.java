package com.softwaremill.java_fp_example.contest.mszarlinski.domain;

import com.softwaremill.java_fp_example.contest.mszarlinski.domain.web.WebPage;
import javaslang.control.Try;

public interface WebPageLoader {
    Try<WebPage> tryLoadWebPage(String url);
}
