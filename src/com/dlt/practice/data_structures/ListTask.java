package com.dlt.practice.data_structures;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

class ListTask implements Callable<List<String>> {

    public static void runListTasks(List<String> listToUpdate) {
        System.out.println("---- " + listToUpdate.getClass().getSimpleName().toUpperCase() + " ----");
        int[] numbers = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };

        System.out.println(listToUpdate);
        ExecutorService executor = Executors.newFixedThreadPool(4);
        List<Future<List<String>>> futures = new ArrayList<>();
        for (Integer number : numbers) {
            futures.add(executor.submit(new ListTask(number, listToUpdate)));
        }
        for (Future<List<String>> future : futures) {
            try {
                List<String> result = future.get();
                System.out.println("LIST STATE (GET LOOP): " + result.toString());
            } catch (Exception e) {
                System.out.println("There was a " + e.getClass().getName() + " exception");
            } 
        }
        executor.shutdown();
    }

    private final Integer number;
    private List<String> listToUpdate;

    public ListTask(Integer number, List<String> listToUpdate) {
        super();
        this.number = number;
        this.listToUpdate = listToUpdate;
    }

    @Override
    public List<String> call() throws Exception {
        Thread.sleep(200);
        BigInteger result = null;
        for (int i = 1; i <= this.number; i++) {
            BigInteger current = BigInteger.valueOf(i);
            result = result == null ? current : result.multiply(current);
        }
        listToUpdate.add(result.toString());
        System.out.println("LIST STATE (CALLABLE CALL): " + listToUpdate.toString());
        Thread.sleep(1000);
        return listToUpdate;
    }
}
