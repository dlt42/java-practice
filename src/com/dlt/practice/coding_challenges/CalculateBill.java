package com.dlt.practice.coding_challenges;

import java.util.Scanner;

public class CalculateBill {
    private double mininimumCharge;
    private double minimumUsage;
    private double unitRate;

    public CalculateBill(double unitRate, double minimumUsage, double mininimumCharge) {
        this.unitRate = unitRate;
        this.minimumUsage = minimumUsage;
        this.mininimumCharge = mininimumCharge;
    }

    public double calculate(double usage) {
        double extraUsage = usage > minimumUsage ? usage - minimumUsage : 0;
        return mininimumCharge + (Math.ceil(extraUsage) * this.unitRate);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CalculateBill billCalculator = new CalculateBill(3.90, 2, 18.84);
        System.out.println("Enter usage: ");
        double usage = scanner.nextDouble();
        System.out.println("Bill amount: " + billCalculator.calculate(usage));
        scanner.close();
    }
}
