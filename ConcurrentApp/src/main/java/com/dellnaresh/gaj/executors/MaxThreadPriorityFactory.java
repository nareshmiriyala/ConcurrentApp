package com.dellnaresh.gaj.executors;

import java.util.concurrent.ThreadFactory;

/**
 * Created by nareshm on 12/1/14.
 */
public class MaxThreadPriorityFactory implements ThreadFactory {
    private static long count = 0;

    public static void main(String[] args) {
        ThreadFactory threadFactory = new MaxThreadPriorityFactory();
        Thread t1 = threadFactory.newThread(new ARunnable());
        System.out.println("The name of the thread is " + t1.getName());
        System.out.println("The priority of the thread is " + t1.getPriority());
        t1.start();
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread temp = new Thread(r);
        temp.setName("prioritythread" + count++);
        temp.setPriority(Thread.MAX_PRIORITY);
        return temp;
    }

    static class ARunnable implements Runnable {
        public void run() {
            System.out.println("Running the created thread ");
        }
    }
}
