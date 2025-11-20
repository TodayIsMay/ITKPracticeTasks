package com.example.itk;

import java.util.concurrent.RecursiveTask;

public class FactorialTask extends RecursiveTask<Long> {
    private static final int LIMIT = 5;
    private final long start;
    private final long end;


    public FactorialTask(int n) {
        this(1, n);
    }

    private FactorialTask(long start, long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        long length = end - start + 1;
        if(length <= LIMIT) {
            return computeDirectly();
        }
        long mid = (start + end) / 2;
        FactorialTask leftTask = new FactorialTask(start, mid);
        FactorialTask rightTask = new FactorialTask(mid + 1, end);

        leftTask.fork();
        long rightResult = rightTask.compute();
        long leftResult = leftTask.join();

        return leftResult * rightResult;
    }

    private Long computeDirectly() {
        long result = 1L;
        for (long i = start; i <= end; i++) {
            result *= i;
        }
        return result;
    }
}