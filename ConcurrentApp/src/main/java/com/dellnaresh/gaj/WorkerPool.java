package com.dellnaresh.gaj;

import java.util.concurrent.*;

/**
 * Created by nareshm on 2/12/2014.
 */
public class WorkerPool implements AbstractPool {
    private ExecutorService executorService = null;
    private int noOfThreads = 5;
    private int blockingQueueSize = 10;
    private BlockingQueue<AbstractJob> taskBlockingDeque = new ArrayBlockingQueue<>(blockingQueueSize);

    public WorkerPool(int noOfThreads, int blockingQueueSize) {
        this.noOfThreads = noOfThreads;
        this.blockingQueueSize = blockingQueueSize;
    }

    @Override
    public void createThreadPool() throws InterruptedException {
        executorService = Executors.newFixedThreadPool(noOfThreads);

    }

    public void addToJobQueue(AbstractJob abstractJob) throws InterruptedException {
        if (!taskBlockingDeque.isEmpty() ) {
            submitJob();
        }
        taskBlockingDeque.put(abstractJob);
        System.out.println("Added Job to Queue and Queue Size is:" + taskBlockingDeque.size());

    }

    public void submitJob() throws InterruptedException {
        //   while(!taskBlockingDeque.isEmpty()) {
        Thread.sleep(1000);
        System.out.println("Submitting task...");
        AbstractJob jobTaken = taskBlockingDeque.take();
        executorService.submit(jobTaken);
        System.out.println("Taken value:" + jobTaken + " Queue Size is:" + taskBlockingDeque.size());
        // }
    }

    public void shutDownPool() {
        executorService.shutdown();
    }

    public void waitTerminatePool() throws InterruptedException {
        executorService.awaitTermination(1, TimeUnit.DAYS);
    }
}

