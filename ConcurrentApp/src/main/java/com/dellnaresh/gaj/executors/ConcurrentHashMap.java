package com.dellnaresh.gaj.executors;

/**
 * Created by nareshm on 12/1/14.
 */
public class ConcurrentHashMap {
    //final java.util.concurrent.ConcurrentHashMap<String,String> concurrentHashMap=new java.util.concurrent.ConcurrentHashMap();
   static java.util.HashMap<String,String> concurrentHashMap=new java.util.HashMap();

    public static void main(String[] args) {

        for(int i=1;i<10000;i++) {

            new Thread() {
                public void run() {
                    System.out.println("The removed element is:" + concurrentHashMap.remove("10"));
                }
            }.start();
            new Thread() {
                public void run() {
                    System.out.println("added element is:" + concurrentHashMap.put("10", "naresh"));
                }
            }.start();
        }
    }
}
