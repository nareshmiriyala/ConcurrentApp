package com.dellnaresh.gaj.executors;

import java.util.concurrent.*;

/**
 * Created by nareshm on 12/1/14.
 */
public class NewCallableTest {

    public static void main(String[] args) {
        Call call=new Call();
        ExecutorService executorService= Executors.newFixedThreadPool(3);

        Future future=executorService.submit(call);
        try {
            System.out.println("value is:"+future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        executorService.shutdown();
    }

    public static class Call implements Callable {
        int i = 1;

        @Override
        public Object call() throws Exception {
            System.out.println("calling callable");
            return i + 5;
        }
    }
}
