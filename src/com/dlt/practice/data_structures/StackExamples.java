package com.dlt.practice.data_structures;

import java.util.ArrayDeque;
import java.util.Deque;

public class StackExamples {
    public static void main(String[] args) {
        Deque<String> stack = new ArrayDeque<>();
        stack.push("a");
        stack.push("b");
        stack.push("c");

        System.out.println("Stack is: " + stack);

        System.out.println("Last item added was: " + stack.peek());

        // poll() removes and returns the last element added to the stack
        // This is the same as pop() except pop() will throw an exception if the stack is empty

        stack.poll();
        System.out.println("Last item added was removed and stack is now: " + stack);

    }
}
