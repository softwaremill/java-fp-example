package com.softwaremill.java_fp_example.contest.barti271.page.content;

import java.util.stream.Stream;

import com.softwaremill.java_fp_example.contest.barti271.page.content.source.Source;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SimpleContent implements Content {

    private static final String OG_IMAGE = "og:image";

    private final Source source;

    public SimpleContent(Source source) {
        this.source = source;
    }

    /**
     * @return stream of Open Graph {@link Image}s read from supplied {@link Source}
     */
    @Override
    public Stream<Image> getOpenGraphImages() {
        return source.getMetaElements()
            .filter(m -> OG_IMAGE.equals(m.getProperty()))
            .map(m -> m::getContent);
    }

    @Override
    public String toString() {
        return source.toString();
    }
}
