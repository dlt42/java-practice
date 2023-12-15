package com.dlt.practice.coding_challenges;

import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WordScore {

    // Java 8
    public static final Map<Character, Integer> letterPointsJava8 = Stream.of(new Object[][] {
            { 'A', 1 }, { 'B', 3 }, { 'C', 3 }, { 'D', 2 }, { 'E', 1 }, { 'F', 4 }, { 'G', 2 }, { 'H', 4 }, { 'I', 1 },
            { 'J', 8 }, { 'K', 5 }, { 'L', 1 }, { 'M', 3 }, { 'N', 1 }, { 'O', 1 }, { 'P', 3 }, { 'Q', 10 }, { 'R', 1 },
            { 'S', 1 }, { 'T', 1 }, { 'U', 1 }, { 'W', 4 }, { 'V', 4 }, { 'X', 8 }, { 'Y', 4 }, { 'Z', 10 }
    }).collect(Collectors.toMap(data -> (Character) data[0], data -> (Integer) data[1]));


    // Java 9
    public static final Map<Character, Integer> letterPointsJava9 = Map.ofEntries(
            Map.entry('A', 1), Map.entry('B', 3), Map.entry('C', 3), Map.entry('D', 2),
            Map.entry('E', 1), Map.entry('F', 4), Map.entry('G', 2), Map.entry('H', 4),
            Map.entry('I', 1), Map.entry('J', 8), Map.entry('K', 5), Map.entry('L', 1),
            Map.entry('M', 3), Map.entry('N', 1), Map.entry('O', 1), Map.entry('P', 3),
            Map.entry('Q', 10), Map.entry('R', 1), Map.entry('S', 1), Map.entry('T', 1),
            Map.entry('U', 1), Map.entry('W', 4), Map.entry('V', 4), Map.entry('X', 8),
            Map.entry('Y', 4), Map.entry('Z', 10));

    public static Stream<Integer> normaliseToChars(String word) {
        return word
                .toUpperCase()
                .trim()
                .chars()
                .filter(Character::isAlphabetic)
                .mapToObj(n -> letterPointsJava9.containsKey((char) n) ? letterPointsJava9.get((char) n) : 0);
    }

    public static int wordScoreCalculator1(String word) {
        return normaliseToChars(word).reduce(0, (total, value) -> total + value);
    }

    public static int wordScoreCalculator2(String word) {
        AtomicInteger score = new AtomicInteger(0);
        normaliseToChars(word).forEach(c -> score.getAndAdd(c));
        return score.get();

    }

    public static void main(String[] args) {
        System.out.println("Enter a word to be scored:");
        Scanner sc = new Scanner(System.in);
        String word = sc.nextLine();
        System.out.println("Approach 1: " + word + " is worth " + wordScoreCalculator1(word));
        System.out.println("Approach 2: " + word + " is worth " + wordScoreCalculator2(word));
        sc.close();
    }

}
