package com.example.itk;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ForkJoinPool;

public class Main {
    private static Generator gen = new Generator();

    public static void main(String[] args) {
        sortingBySum();
        students();
        factorial();
    }

    public static void sortingBySum() {
        List<Order> orders = List.of(
            new Order("Laptop", 1200.0),
            new Order("Smartphone", 800.0),
            new Order("Laptop", 1500.0),
            new Order("Tablet", 500.0),
            new Order("Smartphone", 900.0)
        );

        gen.sortProductsBySumCost(orders);
    }

    public static void students() {
        List<Student> students = Arrays.asList(
            new Student("Student1", Map.of("Math", 90, "Physics", 85)),
            new Student("Student2", Map.of("Math", 95, "Physics", 88)),
            new Student("Student3", Map.of("Math", 88, "Chemistry", 92)),
            new Student("Student4", Map.of("Physics", 78, "Chemistry", 85))
        );

        System.out.println(gen.avgGrades(students));
    }

    public static void factorial() {
        int n = 10;

        ForkJoinPool forkJoinPool = new ForkJoinPool();
        FactorialTask factorialTask = new FactorialTask(n);

        long result = forkJoinPool.invoke(factorialTask);

        System.out.println("Факториал " + n + "! = " + result);
    }
}