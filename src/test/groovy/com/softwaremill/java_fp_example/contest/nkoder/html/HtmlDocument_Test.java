package com.softwaremill.java_fp_example.contest.nkoder.html;

import org.jsoup.Jsoup;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class HtmlDocument_Test {

    @Test
    public void should_find_no_meta_tags_in_empty_html() {
        // given
        HtmlDocument document = new HtmlDocument(Jsoup.parse(
                ""
        ));

        // expect
        assertThat(document.metaTags()).isEmpty();
    }

    @Test
    public void should_find_no_meta_tags_in_html_without_head_section() {
        // given
        HtmlDocument document = new HtmlDocument(Jsoup.parse(
                "<html></html>"
        ));

        // expect
        assertThat(document.metaTags()).isEmpty();
    }

    @Test
    public void should_find_no_meta_tags_in_html_without_them() {
        // given
        HtmlDocument document = new HtmlDocument(Jsoup.parse(
                "<html><head></head></html>"
        ));

        // expect
        assertThat(document.metaTags()).isEmpty();
    }

    @Test
    public void should_find_meta_tag_among_other_tags() {
        // given
        HtmlDocument document = new HtmlDocument(Jsoup.parse(
                "<html><head>" +
                        "<link rel=\"icon\" href=\"favicon.ico\"/>" +
                        "<meta property=\"og:title\" content=\"I'm Meta, The Meta\"/>" +
                        "</head></html>"
        ));

        // expect
        assertThat(document.metaTags())
                .hasSize(1)
                .extracting(HtmlMetaTag::content)
                .contains("I'm Meta, The Meta");
    }

    @Test
    public void should_find_multiple_meta_tags() {
        // given
        HtmlDocument document = new HtmlDocument(Jsoup.parse(
                "<html><head>" +
                        "<meta property=\"og:title\" content=\"Meta, The First\"/>" +
                        "<meta property=\"og:title\" content=\"duplicate\"/>" +
                        "<meta property=\"og:title\" content=\"duplicate\"/>" +
                        "<meta name=\"twitter:card\" value=\"another meta, w/o content\"/>" +
                        "</head></html>"
        ));

        // expect
        assertThat(document.metaTags())
                .hasSize(4)
                .extracting(HtmlMetaTag::content)
                .containsExactly(
                        "Meta, The First",
                        "duplicate",
                        "duplicate",
                        ""
                );
    }

}