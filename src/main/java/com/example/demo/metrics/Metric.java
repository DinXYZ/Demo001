package com.example.demo.metrics;

import java.util.concurrent.TimeUnit;

public interface Metric {
    default void increment(long i) {
    }

    default void record(Runnable runnable) {
    }

    default void record(long time, TimeUnit milliseconds) {
    }
}
