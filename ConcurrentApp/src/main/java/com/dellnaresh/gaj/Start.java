package com.dellnaresh.gaj;

import com.dellnaresh.gaj.newgaj.AbstractJob;

/**
 * Created by nareshm on 2/12/2014.
 */
public class Start {

    public static void main(String[] args) {
        AbstractPool abstractPool = new WorkerPool(10, 10);
        try {
            abstractPool.createThreadPool();
            AbstractJob printJob = new PrintJob("job1");
            for (int i = 0; i < 30; i++) {

                abstractPool.addToJobQueue(printJob);

            }
            //abstractPool.submitJob();
            abstractPool.shutDownPool();
            abstractPool.waitTerminatePool();


        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
