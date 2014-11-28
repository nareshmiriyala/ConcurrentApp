package com.threads;

/**
 * Data Races
 * Threads share memory, and they can concurrently modify data. Since the modification can be done at the same time
 * without safeguards, this can lead to unintuitive results
 * Created by nareshm on 11/28/14.
 */
public class DataRace {
    private static int count = 0;


    public static void main(String[] args) {

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10000; i++) {

                    increment();
                }
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    increment();
                }
            }
        });
        thread1.start();
        thread2.start();
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Value of count is:" + count);

    }

    private static synchronized void increment() {

        count++;
    }
}
