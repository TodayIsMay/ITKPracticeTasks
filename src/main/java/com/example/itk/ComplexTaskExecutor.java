package com.example.itk;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;

public class ComplexTaskExecutor {
    private final int numberOfTasks;

    public ComplexTaskExecutor(int numberOfTasks) {
        this.numberOfTasks = numberOfTasks;
    }

    public void executeTasks(int numberOfTasks) throws InterruptedException, BrokenBarrierException {
        ExecutorService executor = Executors.newFixedThreadPool(numberOfTasks);
        List<Integer> results = Collections.synchronizedList(new ArrayList<>());

        Runnable barrierAction = () -> {
            int combinedResult = 0;
            for (int r : results) {
                combinedResult += r;
            }

            System.out.println("All tasks finished: " + combinedResult);
        };

        CyclicBarrier barrier = new CyclicBarrier(numberOfTasks, barrierAction);

        for (int i = 0; i < numberOfTasks; i++) {
            final int taskId = i + 1;

            executor.submit(() -> {
                try {
                    ComplexTask task = new ComplexTask(taskId);
                    int result = task.call();

                    results.add(result);

                    barrier.await();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }

        executor.shutdown();
        var isClosed = executor.awaitTermination(1, TimeUnit.MINUTES);
        if (!isClosed) {
            System.out.println("Executor didn't finish");
        }
    }
}
