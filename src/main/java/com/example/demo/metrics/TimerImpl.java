package com.example.demo.metrics;

import io.micrometer.core.instrument.Timer;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TimerImpl timer1 = (TimerImpl) o;
        return Objects.equals(timer, timer1.timer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(timer);
    }
}
