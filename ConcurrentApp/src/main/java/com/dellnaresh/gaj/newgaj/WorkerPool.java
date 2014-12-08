package com.dellnaresh.gaj.newgaj;

import java.util.concurrent.*;

public class WorkerPool {
    private static final int corePoolSize = 3;
    private static final int maximumPoolSize = 5;
    private static final long keepAliveTimeInMinutes = 60;
  private static ThreadPoolExecutor executorPool ;
    private static MyMonitorThread monitor;
    private volatile static WorkerPool workerPool = null;

    private WorkerPool() {

    }

    public static WorkerPool getInstance() {
        if (workerPool == null) {
            synchronized (WorkerPool.class) {
                if (workerPool == null) {
                    workerPool = new WorkerPool();
                    try {
                        workerPool.createWorkerPool();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return workerPool;
    }

    private static void createWorkerPool() throws InterruptedException {
        //Get the ThreadFactory implementation to use
        ThreadFactory threadFactory = Executors.defaultThreadFactory();
        //creating the ThreadPoolExecutor
        executorPool = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTimeInMinutes, TimeUnit.MINUTES, new ArrayBlockingQueue<Runnable>(20), threadFactory);
        executorPool.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        //start the monitoring thread
        monitor = new MyMonitorThread(executorPool, 3);
        Thread monitorThread = new Thread(monitor);
        monitorThread.start();
    }

    public static void shutdown() throws InterruptedException {
        //shut down the pool
        executorPool.shutdown();
        executorPool.awaitTermination(1, TimeUnit.DAYS);
        //shut down the monitor thread
        monitor.shutdown();

    }

    public static void deployJob(AbstractJob job) {

        executorPool.submit(job);
    }
}