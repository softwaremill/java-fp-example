package com.softwaremill.java_fp_example.contest.nkoder.html;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.jsoup.nodes.Element;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@RunWith(JUnitParamsRunner.class)
public class HtmlMetaTag_Test {

    @Test
    public void should_know_if_it_contains_given_property() {
        // given
        HtmlMetaTag htmlMetaTag = new HtmlMetaTag(
                new Element("meta").attr("property", "og:title")
        );

        // expect
        assertThat(htmlMetaTag.hasProperty("og:title")).isTrue();
        assertThat(htmlMetaTag.hasProperty("og:image")).isFalse();
    }

    @Parameters(value = {
            "What a Content!",
            " whitespaces   are everywhere     ",
            ""
    })
    @Test
    public void should_provide_its_content(String contentValue) {
        // given
        HtmlMetaTag htmlMetaTag = new HtmlMetaTag(
                new Element("meta").attr("content", contentValue)
        );

        // expect
        assertThat(htmlMetaTag.content()).isEqualTo(contentValue);
    }

    @Test
    public void should_provide_empty_string_if_content_is_absent() {
        // given
        HtmlMetaTag htmlMetaTag = new HtmlMetaTag(
                new Element("meta")
        );

        // expect
        assertThat(htmlMetaTag.content()).isEmpty();
    }

    @Test
    public void should_require_real_meta() {
        assertThatThrownBy(() -> new HtmlMetaTag(
                new Element("not-a-meta")
        )).isInstanceOf(RuntimeException.class)
          .hasMessage("Element has to be a 'meta' tag, but is 'not-a-meta'.");
    }

}
