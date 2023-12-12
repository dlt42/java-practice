package com.dlt.practice.coding_challenges;

import java.text.DecimalFormat;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

public class Sale {

    public static void main(String[] args) {
        System.out.println("\n");
        List<StoreItem> items = List.of(
                new StoreItem("T-Shirt", 19.99, .4),
                new StoreItem("Dress", 34.99, .75),
                new StoreItem("Record Player", 92.99, .75),
                new StoreItem("Hat", 23.99, .1),
                new StoreItem("Jeans", 54.99, .65));

        System.out.println("Store items:\n\n");
        items.forEach((item) -> System.out.println(item));
        System.out.println("\n");
        Optional<StoreItem> leastExpensiveOpt1 = StoreItem.findLeastExpensive1(items);
        Optional<StoreItem> leastExpensiveOpt2 = StoreItem.findLeastExpensive1(items);
        if (leastExpensiveOpt1.isPresent()) {
            System.out.println("1: Least expensive item:\n\n" +
                    leastExpensiveOpt1.get());
        }
         if (leastExpensiveOpt2.isPresent()) {
            System.out.println("2: Least expensive item:\n\n" +
                    leastExpensiveOpt2.get());
        }
    }
}

@Getter
@Setter
@AllArgsConstructor
class StoreItem {
    private static final DecimalFormat df = new DecimalFormat("0.00");

    String name;
    double retailPrice;
    double discount;

    public static Optional<StoreItem> findLeastExpensive1(Collection<StoreItem> items) {
        return items.stream().sorted((a, b) -> a.getDiscountedPrice() < b.getDiscountedPrice() ? -1 : 1).findFirst();
    }

    public static Optional<StoreItem> findLeastExpensive2(Collection<StoreItem> items) {
        return items.stream().min(Comparator.comparing(StoreItem::getDiscountedPrice));
    }

    public double getDiscountedPrice() {
        return retailPrice - (retailPrice * discount);
    }

    @Override
    public String toString() {
        return "Name: " + name + " " +
                "\n  Retail price:    £" + df.format(retailPrice) +
                "\n  Discount:        " + df.format(discount) + "%" +
                "\n  Discounted Price £" + df.format(getDiscountedPrice());
    }
}