package com.softwaremill.java_fp_example.contest.dmydlarz.functional;

import com.google.common.collect.Streams;

import java.util.Iterator;
import java.util.function.Predicate;

public final class Filtered<T> implements Iterable<T> {
    private final Iterable<T> elements;
    private final Predicate<T> predicate;

    public Filtered(Iterable<T> elements, Predicate<T> predicate) {
        this.elements = elements;
        this.predicate = predicate;
    }

    @Override
    public Iterator<T> iterator() {
        return Streams.stream(elements)
                .filter(predicate)
                .iterator();
    }
}
