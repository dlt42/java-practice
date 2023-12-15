package com.dlt.practice.data_structures;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ArrayListExamples {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");

        System.out.println(list);

        list.add(1, "a1");
        System.out.println(list);

        List<String> synchedList = Collections.synchronizedList(list);
        System.out.println(synchedList);

        ListTask.runListTasks(Collections.synchronizedList(new ArrayList<>()));
        ListTask.runListTasks(new ArrayList<>());
    }
}

