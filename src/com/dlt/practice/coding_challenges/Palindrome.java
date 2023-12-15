package com.dlt.practice.coding_challenges;

public class Palindrome {
    private static boolean checkMatchingStrings(String clean) {
         int length = clean.length();
        for (int i = 0; 1 < Math.ceil(length / 2); i++) {
            char leftChar = clean.charAt(i);
            char rightChar = clean.charAt(length - 1 - i);
            if (leftChar != rightChar) {
                return false;
            }
        }
        return true;
    }

    private static boolean checkMatchingPair(String clean) {
        int length = clean.length();
        for (int i = 0; i < length - 1; i++) {
            char currentChar = clean.charAt(i);
            if (i < length - 1 && currentChar == clean.charAt(i + 1) ||
                    i < length - 2 && currentChar == clean.charAt(i + 2)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isPalindrome(String input) {
        String clean = input.replaceAll("\\s", "");
        return checkMatchingPair(clean) || checkMatchingStrings(clean);
    }

    public static void main(String args[]) {
        System.out.println(isPalindrome("abba") == true);
        System.out.println(isPalindrome("abcba") == true);
        System.out.println(isPalindrome("abca") == false);
        System.out.println(isPalindrome("abcdba") == false);
        System.out.println(isPalindrome("z abba n") == true);
        System.out.println(isPalindrome("z abcba n") == true);
        System.out.println(isPalindrome("z abca n") == false);
        System.out.println(isPalindrome("z abcdba n") == false);
    }
}
