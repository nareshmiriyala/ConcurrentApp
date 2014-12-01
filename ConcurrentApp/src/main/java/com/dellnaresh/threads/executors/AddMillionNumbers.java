package com.dellnaresh.threads.executors;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by nareshm on 12/1/14.
 */
public class AddMillionNumbers {
    static class Sum implements Callable<Long>    {
        long start=0;
        long end=0;
        long sum;
        public Sum(long start ,long end){
            this.start=start;
            this.end=end;
        }

        @Override
        public Long call() throws Exception {
           for(long i=start;i<=end;i++){
               sum=sum+i;

           }
            return sum;
        }
    }
    public static void main(String[] args) {
        long calculatedSum=0;
        long N=1000;
        ExecutorService executorService= Executors.newFixedThreadPool(10);

        List<Future<Long>> summationTasks = new ArrayList<>();
        long nByTen = N/10; // divide N by 10 so that it can be submitted as 10 tasks
        for(int i = 0; i < 10; i++) {
// create a summation task
// starting from (10 * 0) + 1 .. (N/10 * 1) to (10 * 9) + 1 .. (N/10 * 10)
            long fromInInnerRange = (nByTen * i) + 1;
            long toInInnerRange = nByTen * (i+1);
            System.out.printf("Spawning thread for summing in range %d to %d %n",
                    fromInInnerRange, toInInnerRange);
// Create a callable object for the given summation range
            Callable<Long> summationTask =
                    new Sum(fromInInnerRange, toInInnerRange);
// submit that task to the executor service
            Future<Long> futureSum = executorService.submit(summationTask);
// it will take time to complete, so add it to the list to revisit later
            summationTasks.add(futureSum);
        }
// now, find the sum from each task
        for(Future<Long> partialSum : summationTasks) {
            try {
// the get() method will block (i.e., wait) until the computation is over
                calculatedSum += partialSum.get();
            } catch(CancellationException | ExecutionException
                    | InterruptedException exception) {
// unlikely that you get an exception - exit in case something goes wrong
                exception.printStackTrace();
                System.exit(-1);
            }
        }
// now calculate the sum using formula (N * (N + 1))/2 without doing the hard-work
        long formulaSum = (N * (N + 1))/2;
// print the sum using formula and the ones calculated one by one
// they must be equal!
        System.out.printf("Sum by threads = %d, sum using formula = %d",
                calculatedSum, formulaSum);
        executorService.shutdown();
    }
}

