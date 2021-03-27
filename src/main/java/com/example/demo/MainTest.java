package com.example.demo;

import com.example.demo.metrics.MetricsService;
import com.example.demo.metricsAOP.MyCounter;
import com.example.demo.metricsAOP.MyTimer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

@Service
public class MainTest {
    private static Random random = new Random();
    @Autowired
    private MetricsService metricsService;

    @Transactional
    @MyCounter(name = "counter", tags = {"", ""}, step = 2)
    @MyTimer(name = "timer", tags = {"", ""})
    public int does(int i) {
//        metricsService.timer("does.run.time.supplier").record(this::does);
        int rnd = random.nextInt(i);
        if (rnd % 2 == 0) {
            throw new RuntimeException("Even : " + rnd);
        }
        return rnd;
    }


//    @SneakyThrows
//    public void does() {
//        metricsService.counter("does.run.count").increment(1);
//        System.out.println("does");
//        Thread.sleep(100);
//        metricsService.timer("does.run.time.record").record(1500, TimeUnit.MILLISECONDS);
//        metricsService.gauge("does.run.gauge", () -> 1.599);
//    }
}
