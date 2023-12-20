package com.dlt.practice.data_structures;

import java.util.ArrayDeque;
import java.util.Queue;

public class QueueExamples {
    
    public static void main(String[] args) {
        Queue<String> queue = new ArrayDeque<>();
        queue.offer("a");
        queue.offer("b");
        queue.offer("c");

        System.out.println("Queue is: " + queue);

        System.out.println("First item added was: " + queue.peek());

        queue.poll(); 
        System.out.println("First item added was removed and stack is now: " + queue);
    }
}
