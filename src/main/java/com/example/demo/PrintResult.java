package com.example.demo;

import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class PrintResult {
    @Autowired
    MainTest mainTest;
    @Autowired
    private MeterRegistry meterRegistry;

    @Scheduled(fixedDelay = 1000)
    void print() {
        System.err.println("-------------------------------------------------------------");
        try {
            System.out.println(mainTest.does(10));
        } catch (Exception e) {
//            e.printStackTrace();
            System.err.println(e);
        }
        System.err.println("-------------------------------------------------------------");
        System.err.println("Result:");
        meterRegistry.getMeters().stream()
//                .filter(m -> m.getId().getName().contains("does"))
                .forEach(meter -> {
                    System.out.println(meter.getId().getName());
                    meter.measure().forEach(System.out::println);
                });
        System.err.println("-------------------------------------------------------------");
    }
}
