package com.example.demo.metrics;

import io.micrometer.core.instrument.Counter;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class CounterImpl implements Metric {
    private final Counter counter;

    public CounterImpl(Counter counter) {
        this.counter = counter;
    }

    @Override
    public void increment(long i) {
        counter.increment(i);
    }
}
