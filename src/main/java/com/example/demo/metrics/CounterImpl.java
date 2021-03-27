package com.example.demo.metrics;

import io.micrometer.core.instrument.Counter;

import java.util.Objects;

public class CounterImpl implements Metric {
    private final Counter counter;

    public CounterImpl(Counter counter) {
        this.counter = counter;
    }

    @Override
    public void increment(long i) {
        counter.increment(i);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CounterImpl counter1 = (CounterImpl) o;
        return Objects.equals(counter, counter1.counter);
    }

    @Override
    public int hashCode() {
        return Objects.hash(counter);
    }
}
