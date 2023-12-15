package com.dlt.practice.data_structures;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class LinkedListExamples {
    public static void main(String[] args) {
        LinkedList<String> list = new LinkedList<>();
        list.add("a");
        list.add("b");
        list.add("c");

        System.out.println(list);

        list.add(1, "a1");
        System.out.println(list);

        List<String> synchedList = Collections.synchronizedList(list);
        System.out.println(synchedList);

        ListTask.runListTasks(Collections.synchronizedList(new LinkedList<>()));
        ListTask.runListTasks(new LinkedList<>());
    }
}
