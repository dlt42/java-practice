package com.dlt.practice.data_structures;

import java.util.Arrays;

public class ArrayExamples {

    public static void main(String[] args) {
        String[] weekdays = new String[7];
        int[] numbers = new int[] { 1,4,2,6,4,7};
        System.out.println(Arrays.toString(weekdays));

        weekdays[0] = "Monday";
        System.out.println(Arrays.toString(weekdays));

        weekdays = new String[] { "mon", "tue", "wed", "thu", "fri", "sat", "sun" };
        System.out.println(Arrays.toString(weekdays));

        for (String day : weekdays) {
            System.out.println("Day:" + day);
        }

        for (int i = 0; i < weekdays.length; i++) {
            weekdays[i] = weekdays[i].toUpperCase();
        }

        System.out.println(Arrays.toString(weekdays));


        System.out.println(Arrays.toString(numbers));
         for (int i = 0; i < numbers.length; i++) {
            numbers[i] += 1;
        }
        System.out.println(Arrays.toString(numbers));
        
        
    }
}
