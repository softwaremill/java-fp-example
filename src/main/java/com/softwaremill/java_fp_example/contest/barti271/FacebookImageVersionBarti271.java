package com.softwaremill.java_fp_example.contest.barti271;

import com.softwaremill.java_fp_example.contest.barti271.page.CachedPage;
import com.softwaremill.java_fp_example.contest.barti271.page.Page;
import com.softwaremill.java_fp_example.contest.barti271.page.SimplePage;
import com.softwaremill.java_fp_example.contest.barti271.page.content.SimpleContent;
import com.softwaremill.java_fp_example.contest.barti271.page.content.jspup.JsoupSource;

public class FacebookImageVersionBarti271 {

    private final Page page;

    public FacebookImageVersionBarti271(String url) {
        this.page = new CachedPage(new SimplePage(new SimpleContent(new JsoupSource(url))));
    }

    @Override
    public String toString() {
        return page.extractImageAddress();
    }

}
