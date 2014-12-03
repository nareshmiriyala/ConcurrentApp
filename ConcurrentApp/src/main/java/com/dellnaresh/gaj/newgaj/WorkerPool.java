package com.dellnaresh.gaj.newgaj;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class WorkerPool {

    public static void main(String args[]) throws InterruptedException{
        //RejectedExecutionHandler implementation
        RejectedExecutionHandlerImpl rejectionHandler = new RejectedExecutionHandlerImpl();
        //Get the ThreadFactory implementation to use
        ThreadFactory threadFactory = Executors.defaultThreadFactory();
        //creating the ThreadPoolExecutor
        ThreadPoolExecutor executorPool = new ThreadPoolExecutor(2, 5, 10, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(2), threadFactory);
        executorPool.setRejectedExecutionHandler(
                new ThreadPoolExecutor.CallerRunsPolicy());
        //start the monitoring thread
        MyMonitorThread monitor = new MyMonitorThread(executorPool, 3);
        Thread monitorThread = new Thread(monitor);
        monitorThread.start();
        //submit work to the thread pool
        for(int i=0; i<10; i++){
            executorPool.submit(new WorkerThread("cmd"+i));
        }

        //shut down the pool
        Thread.sleep(30000);
        executorPool.shutdown();
        executorPool.awaitTermination(1, TimeUnit.DAYS);
        //shut down the monitor thread
        Thread.sleep(5000);
        monitor.shutdown();
        monitorThread.join();



    }
}