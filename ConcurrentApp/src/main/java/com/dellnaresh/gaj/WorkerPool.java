package com.dellnaresh.gaj;

import javafx.concurrent.Task;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by nareshm on 2/12/2014.
 */
public class WorkerPool implements AbstractPool {


    @Override
    public void createThreadPool(int noOfThreads, Task<Object> taskToSubmit) {
        ExecutorService executorService = Executors.newFixedThreadPool(noOfThreads);
        executorService.submit(taskToSubmit);
    }
}
