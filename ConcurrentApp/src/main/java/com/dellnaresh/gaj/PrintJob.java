package com.dellnaresh.gaj;

import com.dellnaresh.gaj.newgaj.AbstractJob;

/**
 * Created by nareshm on 2/12/2014.
 */
public class PrintJob extends AbstractJob {
    public PrintJob(String jobName) {
        super(jobName);
    }

    @Override
    public void run() {
        System.out.println("Print Job:" + Thread.currentThread().getName());
    }
}
