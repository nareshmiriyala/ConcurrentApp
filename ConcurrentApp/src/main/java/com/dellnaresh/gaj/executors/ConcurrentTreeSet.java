package com.dellnaresh.gaj.executors;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * Created by nareshm on 8/12/2014.
 */
public class ConcurrentTreeSet {

    private static PriorityBlockingQueue<BatchQueue> batchQueues =
            new PriorityBlockingQueue<BatchQueue>(20);


    public static void main(String[] args) {

        long batchid1 = 1234;
        for (int i = 0; i < 10000; i++) {

            BatchQueue batchQueue1 = new BatchQueue(batchid1);
            batchQueue1.add(batchid1);
            //   batchQueue1.setQosLevel(i);
            if (batchid1 == 1234) {
                batchQueue1.setQosLevel(1);
            } else if (i % 2 == 0) {
                batchQueue1.setQosLevel(5);
            } else {
                batchQueue1.setQosLevel(3);
            }
            batchQueues.add(batchQueue1);
            batchid1++;
        }
        while (batchQueues.size() != 0) {
            System.out.println(batchQueues.poll());
        }
    }

    public static class BatchQueue extends ConcurrentLinkedQueue<Long> implements Comparable<BatchQueue> {

        private long batchId;
        private String batchType;
        private boolean rollback;
        private String result;
        private Integer qosLevel;

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
            if (!(o instanceof BatchQueue)) return false;

            BatchQueue longs = (BatchQueue) o;

            if (batchId != longs.batchId) return false;

            return true;
        }

        @Override
        public String toString() {
            return "BatchQueue{" +
                    "batchId=" + batchId +
                    ", qosLevel=" + qosLevel +
                    ", batchType='" + batchType + '\'' +
                    ", rollback=" + rollback +
                    ", result='" + result + '\'' +
                    '}';
        }

        @Override
        public int hashCode() {
            return (int) (batchId ^ (batchId >>> 32));
        }


        @Override
        public int compareTo(BatchQueue o) {
            int compareQosLevel = ((BatchQueue) o).getQosLevel();
            //ascending order
            return this.qosLevel - compareQosLevel;

            //descending order
            //return compareQuantity - this.quantity;
        }
    }
}