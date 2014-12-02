package com.dellnaresh.gaj;

/**
 * Created by nareshm on 2/12/2014.
 */
public abstract class AbstractJob implements Runnable {
    String jobName;

    public AbstractJob(String jobName) {
        this.jobName = jobName;
    }

    @Override
    public String toString() {
        return "AbstractJob{} " + jobName;
    }
}
