package com.dlt.practice.annotations_and_reflection;

@CustomAnnotation(priority =  2, tags = {"important","feature"})
public class Info {

    @CustomAnnotation(tags = {"details"})
    public void getDetailedInfo() {
        System.out.println("Some details...");
    }
}
