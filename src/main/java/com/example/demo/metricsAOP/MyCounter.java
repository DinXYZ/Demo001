package com.example.demo.metricsAOP;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MyCounter {
    public String name() default "";

    public String[] tags() default {};

    public double step() default 1.0d;
}
