package com.softwaremill.java_fp_example.contest.hexmind;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

/**
 * The Open Graph protocol enables any web page to become a rich object in a social graph. For instance, this is used on
 * Facebook to allow any web page to have the same functionality as any other object on Facebook.
 *
 * @link http://ogp.me
 */
@Getter
@Builder
public class ExtractionOptions {

    @NonNull
    private final String pageUrl;

    @NonNull
    private final Integer timeoutMs;

    @NonNull
    private final String metaProperty;

    @NonNull
    private final String fallbackContent;

}
