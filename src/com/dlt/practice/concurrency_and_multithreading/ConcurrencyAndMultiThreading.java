package com.dlt.practice.concurrency_and_multithreading;

import java.math.BigInteger;
import java.util.*;
import java.util.concurrent.*;

class ConcurrencyAndMultiThreading {

    static Map<Integer, BigInteger> getFactorials(List<Integer> numbers) {
        ExecutorService executor = Executors.newFixedThreadPool(4);

        // List<Future<Map.Entry<Integer, BigInteger>>> futures = numbers.stream()
        //         .map((number) -> executor.submit(new FactorialTask(number))).collect(Collectors.toList());
        List<Future<Map.Entry<Integer, BigInteger>>> futures = new ArrayList<>();
        for (Integer number : numbers) {
            futures.add(executor.submit(new FactorialTask(number)));
        }

        // Map<Integer, BigInteger> result = futures.stream()
        //      .map((((future) -> {
        //          try {
        //              return future.get();
        //          } catch (InterruptedException | ExecutionException e) {
        //              e.printStackTrace();
        //          }
        //          return null;
        //      })))
        //      .collect(Collectors.toMap(Entry::getKey, Entry::getValue, (a, b) -> b));
        Map<Integer, BigInteger> results = new HashMap<>();
        for (Future<Map.Entry<Integer, BigInteger>> future : futures) {
            try {
                Map.Entry<Integer, BigInteger> result = future.get();
                results.put(result.getKey(), result.getValue());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        executor.shutdown();
        return results;
    }

    public static void main(String[] args) {
        System.out.println(getFactorials(Arrays.asList(20, 5, 25, 10, 30, 15)));
        // Expected output:
        // {
        //      20=2432902008176640000,
        //      5=120,
        //      25=15511210043330985984000000,
        //      10=3628800,
        //      30=265252859812191058636308480000000,
        //      15=1307674368000
        // }
    }
}

class FactorialTask implements Callable<Map.Entry<Integer, BigInteger>> {

    private final Integer number;

    public FactorialTask(Integer number) {
        super();
        this.number = number;
    }

    @Override
    public Map.Entry<Integer, BigInteger> call() throws Exception {
        BigInteger result = null;

        StringBuilder sb = new StringBuilder();
        sb.append("Thread ");
        sb.append(Thread.currentThread().threadId());
        sb.append(" calculating factorial of ");
        sb.append(this.number);
        System.out.println(sb.toString());

        Thread.sleep(1000);

        for (int i = 1; i <= this.number; i++) {
            BigInteger current = BigInteger.valueOf(i);
            result = result == null ? current : result.multiply(current);
        }
        return Map.entry(this.number, result);
    }
}
