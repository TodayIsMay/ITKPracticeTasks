package com.example.itk;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Generator {
    public Map<String, Double> sortProductsBySumCost(List<Order> orders) {
        return orders.stream().collect(Collectors.groupingBy(Order::getProduct, Collectors.summingDouble(Order::getCost)))
            .entrySet()
            .stream()
            .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
            .limit(3)
            .collect(Collectors.collectingAndThen(Collectors.toList(), list -> {
                list.forEach(e -> System.out.println(e.getKey()));
                double sum = list.stream()
                    .mapToDouble(Map.Entry::getValue)
                    .sum();

                System.out.println("Sum: " + sum);
                return null;
            }));
    }
}
