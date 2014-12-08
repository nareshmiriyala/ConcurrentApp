package com.dellnaresh.gaj.newgaj;

/**
 * Created by nareshm on 3/12/2014.
 */
public class StartApp {
    public static void main(String[] args) {
        WorkerPool workerPool = WorkerPool.getInstance();
        AbstractJob job = new WorkerThread("naresh");
        workerPool.deployJob(job);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        AbstractJob job1 = new WorkerThread("suresh");
        workerPool.deployJob(job1);
        AbstractJob job2 = new WorkerThread("ramesh");
        workerPool.deployJob(job2);

        try {
            workerPool.shutdown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
