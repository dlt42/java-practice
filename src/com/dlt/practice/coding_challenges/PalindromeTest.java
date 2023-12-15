package com.dlt.practice.coding_challenges;

import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PalindromeTest {

    private final ByteArrayOutputStream printOut = new ByteArrayOutputStream();

    @Before
    public void setUpEach() {
        System.setOut(new PrintStream(printOut));
    }

    @After
    public void cleanUpEach() {
        System.setOut(System.out);
    }

    @Test
    public void isSplitPlaindrome() {
        assertTrue(Palindrome.isPalindrome("abba"));
    }

    @Test
    public void isCenterPalindrome() {
        System.out.println(Palindrome.isPalindrome("abcba") == true);
    }

    @Test
    public void isNotSplitPalindrome() {
    assertTrue(Palindrome.isPalindrome("abca") == false);
    }

    @Test
    public void isNotCenterPalindrome() {
        assertTrue(Palindrome.isPalindrome("abcdba") == false);
    }

    @Test
    public void isSubSplitPlaindrome() {
        assertTrue(Palindrome.isPalindrome("z abba n") == true);
    }

     @Test
    public void isSubCenterPalindrome() {
        assertTrue(Palindrome.isPalindrome("z abcba n") == true);
    }

    @Test
    public void isNotSubSplitPlaindrome() {
        assertTrue(Palindrome.isPalindrome("z abca n") == false);
    }

     @Test
    public void isNotSubCenterPalindrome() {
        assertTrue(Palindrome.isPalindrome("z abcdba n") == false);
    }
}