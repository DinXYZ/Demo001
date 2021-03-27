package com.example.demo.metrics;

import io.micrometer.core.instrument.Gauge;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(of = "gauge")
public class GaugeImpl implements Metric {
    private final Gauge gauge;

    public GaugeImpl(Gauge gauge) {
        this.gauge = gauge;
    }
}
