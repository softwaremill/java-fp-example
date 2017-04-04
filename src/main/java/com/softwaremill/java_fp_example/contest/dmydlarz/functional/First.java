package com.softwaremill.java_fp_example.contest.dmydlarz.functional;

import java.util.Iterator;

public final class First<T> implements UnsafeSupplier<T> {
    private final Iterable<T> iterable;

    public First(Iterable<T> iterable) {
        this.iterable = iterable;
    }

    @Override
    public T get() throws Exception {
        Iterator<T> iterator = iterable.iterator();
        if(!iterator.hasNext()) {
            throw new Exception("Missing elements");
        }
        return iterator.next();
    }
}
