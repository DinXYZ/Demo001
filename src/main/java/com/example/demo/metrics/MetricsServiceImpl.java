package com.example.demo.metrics;

import io.micrometer.core.instrument.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;

@Service
public class MetricsServiceImpl implements MetricsService {
    private static Map<Meter.Id, Metric> metrics = new ConcurrentHashMap<>();
    @Autowired
    private MeterRegistry meterRegistry;

    @Override
    public Metric counter(String name, String... tags) {
        Counter counter = Counter.builder(name).tags(tags).register(meterRegistry);
        if (metrics.containsKey(counter.getId())) {
            Metric metric = metrics.get(counter.getId());
            return metric;
        }
        CounterImpl newCounter = new CounterImpl(counter);
        metrics.put(counter.getId(), newCounter);
        return newCounter;
    }

    @Override
    public Metric timer(String name, String... tags) {
        Timer timer = Timer.builder(name).tags(tags).register(meterRegistry);
        if (metrics.containsKey(timer.getId())) {
            Metric metric = metrics.get(timer.getId());
            return metric;
        }
        TimerImpl newTimer = new TimerImpl(timer);
        metrics.put(timer.getId(), newTimer);
        return newTimer;
    }

    @Override
    public Metric gauge(String name, Supplier<Number> supplier, String... tags) {
        Gauge gauge = Gauge.builder(name, supplier).tags(tags).register(meterRegistry);
        if (metrics.containsKey(gauge.getId())) {
            Metric metric = metrics.get(gauge.getId());
            return metric;
        }
        GaugeImpl newGauge = new GaugeImpl(gauge);
        metrics.put(gauge.getId(), newGauge);
        return newGauge;
    }


}
