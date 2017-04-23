package com.softwaremill.java_fp_example.contest.adamsiemion;

import com.softwaremill.java_fp_example.DefaultImage;

interface OpenGraphImage {
    String content();

    OpenGraphImage DEFAULT_IMAGE = () -> DefaultImage.DEFAULT_IMAGE;
}
