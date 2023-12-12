package com.dlt.practice.annotations_and_reflection;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public class AnnotationsAndReflection {

    public static void main(String[] args) throws Exception {
        Class<Info> classData = Info.class;
        if (classData.isAnnotationPresent(CustomAnnotation.class)) {
            CustomAnnotation annotation = classData.getAnnotation(CustomAnnotation.class);
            System.out.println("Priority: " + annotation.priority());
            System.out.println("Tags: " + Arrays.toString(annotation.tags()));
        }

        for (Method method : classData.getDeclaredMethods()) {
            if (method.isAnnotationPresent(CustomAnnotation.class)) {
                CustomAnnotation annotation = method.getAnnotation(CustomAnnotation.class);
                System.out.println("Method: " + method.getName());
                System.out.println("Priority: " + annotation.priority());
                System.out.println("Tags: " + Arrays.toString(annotation.tags()));
            }
        }

        Class<?> infoClass = Class.forName("com.dlt.practice.annotations_and_reflection.Info");
        Object instance = infoClass.getDeclaredConstructor().newInstance();
        Method method = infoClass.getMethod("getDetailedInfo");
        method.invoke(instance);

        Method testMethod = TestDescription.class.getMethod("doSomething");
        if (testMethod.isAnnotationPresent(MethodDescription.class)) {
            MethodDescription description = testMethod.getAnnotation(MethodDescription.class);
            System.out.println("Description: " + description.value());
        };
    }
}

class TestDescription {

    @MethodDescription ("Finding the answer") //  or @MethodDescription (value = "Finding the answer")
    public static void doSomething() {

    }

}

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@interface MethodDescription {
    String value() default "";
}