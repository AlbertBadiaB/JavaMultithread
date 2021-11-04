/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ba.rrhh.multithread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 *
 * @author albert.badia
 */
public class FutureTaskExample {

    public static void main(String[] args) {
        MyRunnable myrunnableObject1 = new MyRunnable(1000);
        MyRunnable myrunnableObject2 = new MyRunnable(2000);

        FutureTask<String> futureTask1 = new FutureTask<>(myrunnableObject1,
                "FutureTask1 is complete");
        FutureTask<String> futureTask2 = new FutureTask<>(myrunnableObject2,
                "FutureTask2 is complete");

        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.submit(futureTask1);
        executor.submit(futureTask2);

        while (true) {
            try {
                while (!futureTask2.isDone()) {
                    System.out.println("Calculating...");
                }
                while (!futureTask1.isDone() && futureTask2.isDone()) {
                    System.out.println("Both FutureTask Complete");
                    System.out.println("FutureTask1 output ="
                            + futureTask1.get());
                    System.out.println("FutureTask2 output ="
                            + futureTask2.get());
                    executor.shutdown();
                    return;
                }
            } catch (Exception e) {
                System.out.println("Exception " + e);
            }
        }
    }
}
