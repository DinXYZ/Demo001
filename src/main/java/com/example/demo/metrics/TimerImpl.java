package com.example.demo.metrics;

import io.micrometer.core.instrument.Timer;
import lombok.EqualsAndHashCode;

import java.util.concurrent.TimeUnit;

@EqualsAndHashCode
public class TimerImpl implements Metric {
    private final Timer timer;

    public TimerImpl(Timer timer) {
        this.timer = timer;
    }

    @Override
    public void record(Runnable runnable) {
        timer.record(runnable);
    }

    @Override
    public void record(long time, TimeUnit milliseconds) {
        timer.record(time, milliseconds);
    }
}
