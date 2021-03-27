package com.example.demo.metrics;

import io.micrometer.core.instrument.Gauge;

import java.util.Objects;

public class GaugeImpl implements Metric {
    private final Gauge gauge;

    public GaugeImpl(Gauge gauge) {
        this.gauge = gauge;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GaugeImpl gauge1 = (GaugeImpl) o;
        return Objects.equals(gauge, gauge1.gauge);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gauge);
    }
}
