package com.dellnaresh.threads.executors;

import java.util.Collections;
import java.util.Map;
import java.util.HashMap;
import java.util.Hashtable;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * HashMapInfiniteLoopSimulator
 * @author Pierre-Hugues Charbonneau
 *
 */
public class HashMapInfiniteLoopSimulator {

    private static final int NB_THREADS = 50;
    private static final int NB_TEST_ITERATIONS = 50;

    private static Map<String, Integer> assignedMapForTest = null;
    private static Map<String, Integer> nonThreadSafeMap = null;
    private static Map<String, Integer> threadSafeMap1 = null;
    private static Map<String, Integer> threadSafeMap2 = null;
    private static Map<String, Integer> threadSafeMap3 = null;

    /**
     * Main program
     * @param args
     */
    public static void main(String[] args) {


     //   for (int i=0; i<NB_TEST_ITERATIONS; i++) {

            // Plain old HashMap (since JDK 1.2)
            nonThreadSafeMap = new HashMap<String, Integer>(2);

            // Plain old Hashtable (since JDK 1.0)
            threadSafeMap1 = new Hashtable<String, Integer>(2);

            // Fully synchronized HashMap
            threadSafeMap2 = new HashMap<String, Integer>(2);
            threadSafeMap2 = Collections.synchronizedMap(threadSafeMap2);

            // ConcurrentHashMap (since JDK 1.5)
            threadSafeMap3 = new ConcurrentHashMap<String, Integer>(2); // ConcurrentHashMap


            /*** Assign map at your convenience ****/
            assignedMapForTest = nonThreadSafeMap;


            long timeBefore = System.currentTimeMillis();
            long timeAfter = 0;
            Float totalProcessingTime = null;

            ExecutorService executor = Executors.newFixedThreadPool(NB_THREADS);

            for (int j = 0; j < NB_THREADS; j++) {

                /** Assign the Map at your convenience **/
                Runnable worker = new WorkerThread(assignedMapForTest);
                executor.execute(worker);
            }

            // This will make the executor accept no new threads
            // and finish all existing threads in the queue
            executor.shutdown();
            // Wait until all threads are finish
            while (!executor.isTerminated()) {

            }

            timeAfter = System.currentTimeMillis();
            totalProcessingTime = new Float( (float) (timeAfter - timeBefore) / (float) 1000);

            System.out.println("All threads completed in "+totalProcessingTime+" seconds");

        System.out.println("Map size:"+assignedMapForTest.size()+" Data inserted:"+(NB_THREADS *500000));
       // }
    }

}