package com.dellnaresh.gaj.executors;

import java.util.Comparator;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by nareshm on 11/12/2014.
 */
public class ProduceBatchJobs {

    //private static Map<Long, BatchQueue> batchQueues = new java.util.concurrent.ConcurrentHashMap<>();
    private static AtomicInteger producerCount = new AtomicInteger(0);
    private static AtomicInteger consumerCount = new AtomicInteger(0);
    private static Comparator<BatchQueue> comparator = new QOSComparator();
    private static BlockingQueue<BatchQueue> batchQueues = new LinkedBlockingQueue<>(5);
    // private static Iterator<BatchQueue> batchQueueIterator = batchQueues.values().iterator();

    public static void main(String[] args) {

        Thread thread = new Thread(new Producer());
        thread.start();

        Thread consumerT = new Thread(new Consumer());
        consumerT.start();
    }

    private static class Consumer implements Runnable {
        private static void getBatchId() {
            if (batchQueues.peek() != null) {

                BatchQueue batchQueue = batchQueues.poll();
                Long transId = batchQueue.poll();
                //System.out.println("batch trans ids:" + transId);

            }
        }

        @Override
        public void run() {


            while (true) {
                try {

                    Thread.sleep(3000);
                    System.out.println("Consumer queue size:" + batchQueues.size());
                    getBatchId();
                    //  System.out.println("Consumed count:"+consumerCount.incrementAndGet());
                } catch (InterruptedException e) {
                    e.printStackTrace();

                }
            }
        }
    }

    private static class Producer implements Runnable {
        private static void updateBatchQueueMap() {

            for (int i = 0; i < 5; i++) {
                BatchQueue batchQueue1 = new BatchQueue(1234);
                batchQueue1.add(1234l);

                batchQueue1.setQosLevel(3);

                try {
                    System.out.println("Producer queue size:" + batchQueues.size());
                    //    System.out.println("Produced count:"+producerCount.incrementAndGet());
                    // Thread.sleep(2000);
                    //  System.out.println("Produced a new BatchQueue and added ");
                    batchQueues.put(batchQueue1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }

        @Override
        public void run() {
            while (true) {
                if (batchQueues.size() == 0) {
                    updateBatchQueueMap();
                }
            }
        }
    }

    public static class BatchQueue extends ConcurrentLinkedQueue<Long> implements Comparable<BatchQueue> {

        private long batchId;
        private String batchType;
        private boolean rollback;
        private String result;
        private int qosLevel;

        BatchQueue(long batchId) {
            this(batchId, false, null);
        }

        BatchQueue(long batchId, boolean rollback, String result) {
            this.batchId = batchId;
            this.rollback = rollback;
            this.result = result;
        }

        public long getBatchId() {
            return batchId;
        }

        public void setBatchId(long batchId) {
            this.batchId = batchId;
        }

        public String getBatchType() {
            return batchType;
        }

        public void setBatchType(String batchType) {
            this.batchType = batchType;
        }

        public boolean isRollback() {
            return rollback;
        }

        public void setRollback(boolean rollback) {
            this.rollback = rollback;
        }

        public String getResult() {
            return result;
        }

        public void setResult(String result) {
            this.result = result;
        }

        public int getQosLevel() {
            return qosLevel;
        }

        public void setQosLevel(int qosLevel) {
            this.qosLevel = qosLevel;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            BatchQueue that = (BatchQueue) o;

            return (batchId == that.batchId);
        }

        @Override
        public int compareTo(BatchQueue o) {
            return 0;
        }
    }

    private static class QOSComparator implements Comparator<BatchQueue> {
        @Override
        public int compare(BatchQueue x, BatchQueue y) {
            if (x.getQosLevel() < y.getQosLevel()) {
                return -1;
            }
            if (x.getQosLevel() > y.getQosLevel()) {
                return 1;
            }
            return 0;
        }
    }
}
