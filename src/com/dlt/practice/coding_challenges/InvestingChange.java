package com.dlt.practice.coding_challenges;

import java.text.DecimalFormat;
import java.util.List;

// Calculate average change invested 

public class InvestingChange {
    private static final DecimalFormat df = new DecimalFormat("0.00");

    private List<Double> purchases;

    public InvestingChange(List<Double> purchases) {
        this.purchases = purchases;
    }

    public double calculate1() {
        return this.purchases
                .stream()
                .mapToDouble(val -> Math.ceil(val) - val)
                .reduce(0, (total, value) -> total + value) / purchases.size();
    }

    public double calculate2() {
        return purchases
                .stream()
                .mapToDouble(val -> Math.ceil(val) - val)
                .average()
                .orElse(0);
    }

    public String getCalculate1String() {
        return df.format(calculate1());
    }

    public String getCalculate2String() {
        return df.format(calculate2());
    }

    public static void main(String[] args) {
        List<Double> purchases = List.of(12.38, 38.29, 5.27, 3.21);
        InvestingChange investingChange = new InvestingChange(purchases);

        System.out.println(investingChange.getCalculate1String());
        System.out.println(investingChange.getCalculate2String());
    }
}