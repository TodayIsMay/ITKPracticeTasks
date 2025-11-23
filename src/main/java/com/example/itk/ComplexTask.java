package com.example.itk;

import java.util.concurrent.Callable;

public class ComplexTask implements Callable<Integer> {
    private final int taskId;
    private int partialResult;

    public ComplexTask(int taskId) {
        this.taskId = taskId;
    }

    public Integer call() throws Exception {
        System.out.println("Task " + taskId + " started execution.");

        Thread.sleep(1_000);

        partialResult = taskId + 12;

        System.out.println("Task " + taskId + " finished with result " + partialResult);
        return partialResult;
    }
}
