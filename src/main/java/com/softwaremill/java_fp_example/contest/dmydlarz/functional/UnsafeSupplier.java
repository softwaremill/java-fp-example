package com.softwaremill.java_fp_example.contest.dmydlarz.functional;

@FunctionalInterface
public interface UnsafeSupplier<T> {
    T get() throws Exception;
}
