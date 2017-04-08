package com.softwaremill.java_fp_example.contest.maciejdobrowolski;

import static com.softwaremill.java_fp_example.DefaultImage.DEFAULT_IMAGE;
import static io.reactivex.schedulers.Schedulers.io;
import static java.util.concurrent.TimeUnit.SECONDS;

import com.github.florent37.retrojsoup.RetroJsoup;
import io.reactivex.Maybe;
import io.reactivex.ObservableTransformer;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FacebookImage {

    private final static String FACEBOOK_IMAGE_TAG = "og:image";
    private final static MetaEntry FALLBACK_META_ENTRY = new MetaEntry(FACEBOOK_IMAGE_TAG, DEFAULT_IMAGE);
    private final static int TIMEOUT_IN_SECONDS = 10;

    private final String url;

    public FacebookImage(String url) {
        this.url = url;
    }

    public Maybe<String> imageAddress() {
        return pageModel().metaTags()
                .subscribeOn(io())
                .compose(defaultOnTimeout())
                .filter(FacebookImage::isFacebookImageTag)
                .compose(firstOrDefault())
                .singleElement()
                .map(meta -> meta.content);
    }

    private PageModel pageModel() {
        return new RetroJsoup.Builder()
                .url(url)
                .build()
                .create(PageModel.class);
    }

    private ObservableTransformer<MetaEntry, MetaEntry> defaultOnTimeout() {
        return upstream -> upstream.timeout(TIMEOUT_IN_SECONDS, SECONDS)
                .doOnError(e -> log.error("Unable to extract og:image from url {}. Problem: {}", url, e.getMessage()))
                .onErrorReturnItem(FALLBACK_META_ENTRY);
    }

    private static boolean isFacebookImageTag(MetaEntry metaEntry) {
        return FACEBOOK_IMAGE_TAG.equals(metaEntry.attr);
    }

    private ObservableTransformer<MetaEntry, MetaEntry> firstOrDefault() {
        return upstream -> upstream.firstOrError()
                .doOnError(e -> log.warn("No {} found for blog post {}", FACEBOOK_IMAGE_TAG, url))
                .onErrorReturnItem(FALLBACK_META_ENTRY)
                .toObservable();
    }

}
