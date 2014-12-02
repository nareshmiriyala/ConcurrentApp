package com.dellnaresh.gaj;

/**
 * Created by nareshm on 2/12/2014.
 */
public interface AbstractPool {

    public void createThreadPool() throws InterruptedException;

    public void addToJobQueue(AbstractJob abstractJob) throws InterruptedException;

    public void submitJob() throws InterruptedException;

    public void waitTerminatePool() throws InterruptedException;

    public void shutDownPool();
}
