package com.example.demo.metrics;

import java.util.function.Supplier;

public interface MetricsService {
    Metric counter(String name, String... tags);

    Metric timer(String name, String... tags);

    Metric gauge(String name, Supplier<Number> supplier, String... tags);
}
