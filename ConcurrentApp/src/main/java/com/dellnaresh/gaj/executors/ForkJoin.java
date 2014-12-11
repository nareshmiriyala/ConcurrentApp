package com.dellnaresh.gaj.executors;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * Created by nareshm on 12/1/14.
 */
public class ForkJoin {
    private static final int NUM_THREADS=10;
    private static final int NO_OF_PROCESSORS=Runtime.getRuntime().availableProcessors();
    private static long N = 1000;

    public static void main(String[] args) {
        System.out.println("NO of processors:" + NO_OF_PROCESSORS);
        ForkJoinPool forkJoinPool = new ForkJoinPool(NUM_THREADS);
        long computedSum = forkJoinPool.invoke(new RecursiveSUM(0, N));
        long formulaSum = (N * (N + 1)) / 2;
        System.out.printf("Sum for range 1..%d computed sum= %d,formula sum =%d %n", N, computedSum, formulaSum);
    }

    static class RecursiveSUM extends RecursiveTask<Long>{
        long from,to;
        public RecursiveSUM(long from,long to){
            this.from=from;
            this.to=to;
        }
        @Override
        protected Long compute() {
            long localSum=0;
            if((to-from)<=N/NO_OF_PROCESSORS) {

                for (long i = from; i <= to; i++) {
                    localSum = localSum + i;
                }

                System.out.printf("\t Summing of value range %d to %d is %d %n", from, to, localSum);
                return localSum;
            }else {
                long mid=(from+to)/2;
                System.out.printf("Forking computation into two ranges:"+"%d to %d and %d to %d %n",from,mid,mid,to);
                RecursiveSUM firstHalf=new RecursiveSUM(from,mid);
                firstHalf.fork();
                RecursiveSUM secondHalf=new RecursiveSUM(mid+1,to);
                long resultSecond=secondHalf.compute();
                return  firstHalf.join()+resultSecond;

            }
        }
    }
}
