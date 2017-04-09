package com.softwaremill.java_fp_example.contest.mszarlinski.domain;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

/**
 * Immutable value object representing a FB image.
 */
public class FacebookImage {

    public final static String FACEBOOK_IMAGE_TAG = "og:image";

    public static final FacebookImage DEFAULT = new FacebookImage("https://softwaremill.com/images/logo-vertical.023d8496.png");

    private final String url;

    public FacebookImage(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FacebookImage that = (FacebookImage) o;
        return Objects.equal(url, that.url);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(url);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("url", url)
                .toString();
    }
}
