package com.dlt.practice.coding_challenges;

import java.util.Scanner;

public class PasswordComplexity {

    // At least six characters with at least:
    // One uppercase
    // One lowercase
    // One number

    // Ok
    public static boolean isPasswordComplex1(String password) {
        if (password.length() < 6)
            return false;
        boolean hasLowercaseLetter = false;
        boolean hasUppercaseLetter = false;
        boolean hasNumber = false;
        for (int i = 0; i < password.length() && (!hasLowercaseLetter || !hasUppercaseLetter || !hasNumber); i++) {
            char current = password.charAt(i);
            if (Character.isDigit(current)) {
                hasNumber = true;
            } else if (Character.isUpperCase(current)) {
                hasUppercaseLetter = true;
            } else if (Character.isLowerCase(current)) {
                hasLowercaseLetter = true;
            }
        }

        return hasLowercaseLetter && hasUppercaseLetter && hasNumber;
    }

    // Better
    public static boolean isPasswordComplex2(String password) {
        return password.length() >= 6
                && password.matches(".*\\d.*")
                && password.matches(".*[a-z].*")
                && password.matches(".*[A-Z].*");
    }

    // Best
    public static boolean isPasswordComplex3(String password) {
        return password.matches("^(?=.*[A-Z])(?=.*[0-9])(?=.*[a-z]).{6,}$");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a password: ");
        String password = scanner.next();
        System.out.println("Is the password complex? " + isPasswordComplex2(password));
        scanner.close();
    }
}
