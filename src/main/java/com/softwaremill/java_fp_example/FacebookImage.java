package com.softwaremill.java_fp_example;

public interface FacebookImage {

    String FACEBOOK_IMAGE_TAG = "og:image";
    String DEFAULT_IMAGE = "https://softwaremill.com/images/logo-vertical.023d8496.png";
    static final int TEN_SECONDS = 10000;
    
    String extractImageAddressFrom(String pageUrl);
    
}
