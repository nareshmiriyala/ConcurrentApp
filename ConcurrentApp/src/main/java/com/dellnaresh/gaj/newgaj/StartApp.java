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
        WorkerPool workerPool2 = WorkerPool.getInstance();
        AbstractJob job3 = new WorkerThread("pooltest");
        workerPool2.deployJob(job3);
//        try {
//            workerPool.shutdown();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }
}
