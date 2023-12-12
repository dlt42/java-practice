package com.dlt.practice;


import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Practice {

    public static void main(String[] args) {
        try {

            Predicate<String> largerThan5 = s -> s.length() > 5;
            boolean result = largerThan5.test("Hello");

            Consumer<String> printUpperCase = s -> System.out.println(s.toUpperCase());
            Consumer<String> toUpperCase = String::toUpperCase;
            printUpperCase.accept("result = " + result);
            toUpperCase.accept("abc");
            System.out.println();

            doSomething(largerThan5);
            doSomething(s -> s.startsWith("A"));

            Person jerry = DataStore.getInstance().getPerson("Jerry");
            System.out.println("Test " + jerry.toJSOString());

            int[] numbers = { 1, 2, 3, 4, 5 };
            int sum = Arrays.stream(numbers).sum();
            System.out.println("Sum " + sum);

            // List<String> names = Arrays.asList();
            // Stream<String> nameStream = names.stream();

            // String[] namesArray = { "A", "B", "C" };
            // Stream<String> nameArrayStream = Arrays.stream(namesArray);

            Stream<String> namesOfStream = Stream.of("A", "B", "C");
            Stream<String> resault = namesOfStream.filter(s -> s.equals("A"));
            System.out.println(resault.collect(Collectors.toList()));

            List<String> moreNames = Arrays.asList("Aa", "Ba", "Bb", "Ca", "Cb");
            List<String> filteredNames = moreNames
                    .stream()
                    .filter(name -> name.contains("a"))
                    .map(String::toUpperCase)
                    .collect(Collectors.toList());
            System.out.println(filteredNames);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void doSomething(Predicate<String> p) {
        System.out.println(p.test("doSomething"));
    }
}


/**
 * The HashMap should be used when we do not require key-value pair in sorted order.	
 * 
 * The TreeMap should be used when we require key-value pair in sorted (ascending) order.
 */