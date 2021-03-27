package com.example.demo.metricsAOP;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class Metrics {
    @Autowired
    private MeterRegistry meterRegistry;

    @Around("@annotation(MyCounter)")
    public Object counter(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        MyCounter myCounter = method.getAnnotation(MyCounter.class);
        String name = generateName(method, myCounter.name());
        Counter counter = Counter.builder(name).tags(myCounter.tags()).register(meterRegistry);
        Counter counterEx = Counter.builder(name + ".Exceptions").tags(myCounter.tags()).register(meterRegistry);
        try {
            counter.increment(myCounter.step());
            Object proceed = joinPoint.proceed();
            return proceed;
        } catch (Exception e) {
            counterEx.increment();
            throw e;
        }
    }

    @Around("@annotation(MyTimer)")
    public Object timer(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        MyTimer myTimer = method.getAnnotation(MyTimer.class);
        String name = generateName(method, myTimer.name());
        Timer.Sample start = Timer.start();
        try {
            Object proceed = joinPoint.proceed();
            return proceed;
        } catch (Exception e) {
            Counter counterEx = Counter.builder(name + ".Exceptions").tags(myTimer.tags()).register(meterRegistry);
            counterEx.increment();
            throw e;
        } finally {
            start.stop(Timer.builder(name).tags(myTimer.tags()).register(meterRegistry));
        }
    }

    private String generateName(Method method, String name) {
        return method.getDeclaringClass().getName() + "." + method.getName() + "." + name;
    }
}
