package com.threads;

/**
 * Thread.sleep and join example
 * Created by nareshm on 11/28/14.
 */
public class TimeBomb {
    static class Timer implements Runnable{


        @Override
        public void run() {
            for(int i=9;i>0;i--){
                System.out.println(i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    public static void main(String[] args) {
        Timer timer=new Timer();
        Thread thread=new Thread(timer);
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Boom!!!");
    }
}
