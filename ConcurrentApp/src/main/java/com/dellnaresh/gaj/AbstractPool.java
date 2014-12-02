package com.dellnaresh.gaj;

import javafx.concurrent.Task;

/**
 * Created by nareshm on 2/12/2014.
 */
public interface AbstractPool {

    public void createThreadPool(int noOfThreads, Task<Object> taskToSubmit);
}
