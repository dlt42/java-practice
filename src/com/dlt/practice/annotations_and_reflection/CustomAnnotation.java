package com.dlt.practice.annotations_and_reflection;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface CustomAnnotation {
    int priority() default 1;

    String[] tags() default {};
}