package com.dlt.practice.data_structures;

import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.Deque;
import java.util.Set;
import java.util.TreeSet;

public class TreeSetExamples {
    public static void main(String[] args) {
        Set<Integer> treeSet = new TreeSet<>();
        treeSet.add(1);
        treeSet.add(300);
        treeSet.add(47);
        treeSet.add(6);
        System.out.println("TreeSet is: " + treeSet);
        treeSet.add(6);
        System.out.println("Tried to add 6 again but TreeSet is still: " + treeSet);

        Set<String> wordSet1 = new TreeSet<>();
        wordSet1.add("aaa");
        wordSet1.add("ab");
        wordSet1.add("acaa");
        wordSet1.add("bbC");
        System.out.println("TreeSet using default comparator: " + wordSet1);

        Set<String> wordSet2 = new TreeSet<>(Comparator.comparing(String::length));
        wordSet2.add("aaa");
        wordSet2.add("ab");
        wordSet2.add("acaa");
        wordSet2.add("bbc");
        System.out.println("TreeSet using length comparator (last item no present as lenght was same as previously added item): " + wordSet2);
        wordSet2.remove("ab");
        System.out.println("After removing 'ab', TreeSet using length comparator is now: " + wordSet2);
    }
}
