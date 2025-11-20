package com.example.itk;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Generator {
    public void sortProductsBySumCost(List<Order> orders) {
        orders.stream().collect(Collectors.groupingBy(Order::getProduct, Collectors.summingDouble(Order::getCost)))
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

    public Map<String, Double> avgGrades(List<Student> students) {
        return students.parallelStream()
            .flatMap(student -> student.getGrades().entrySet().stream())
            .collect(Collectors.groupingBy(
                Map.Entry::getKey,
                Collectors.averagingInt(Map.Entry::getValue)
            ));
    }
}
