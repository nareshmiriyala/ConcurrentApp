package com.dellnaresh.threads.executors;

import java.util.Map;

/**
 * WorkerThread
 *
 * @author Pierre-Hugues Charbonneau
 *
 */
public class WorkerThread implements Runnable {

    private Map<String, Integer> map = null;

    public WorkerThread(Map<String, Integer> assignedMap) {
        this.map = assignedMap;

    }

    @Override
    public void run() {

        for (int i=0; i<500000; i++) {
            // Return 2 integers between 1-1000000 inclusive
            Integer newInteger1 = i;
            Integer newInteger2 = i;


            // 2. Attempt to insert a random Integer element
            map.put(String.valueOf(newInteger2), newInteger2);

            // 1. Attempt to retrieve a random Integer element
            Integer retrievedInteger = map.get(String.valueOf(newInteger1));

         //   System.out.println("Retrieved value is:"+retrievedInteger);

        }
    }

}