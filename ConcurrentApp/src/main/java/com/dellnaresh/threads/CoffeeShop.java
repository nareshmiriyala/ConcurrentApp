package com.dellnaresh.threads;

import java.math.BigDecimal;

/**
 * Created by nareshm on 29/11/2014.
 */
public class CoffeeShop {

    public static void main(String[] args) {
        Customer customer = new Customer();
        Thread cusThread = new Thread(customer);
        Waiter waiter = new Waiter();
        Thread waiterThread = new Thread(waiter);
        cusThread.start();
        waiterThread.start();
        try {
            cusThread.join();
            waiterThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    static class Waiter implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                System.out.println(" Waiter Waiting for Order");
                synchronized (Coffee.class) {
                    try {
                        Coffee.class.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(" Waiter Received order for coffee");
                System.out.println("Waiter Delivered the coffee");
                synchronized (Coffee.class) {
                    Coffee.class.notifyAll();
                }
            }
        }
    }

    static class Customer implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                placeOrder(i);
                System.out.println(" Customer Received the coffee");
            }
        }

        private void placeOrder(int i) {
            System.out.println("Customer No " + i + " Ordered Coffee ");
            synchronized (Coffee.class) {
                Coffee.class.notifyAll();
            }
            synchronized (Coffee.class) {
                try {
                    System.out.println("Customer Waiting for Coffee........");
                    Coffee.class.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    static class Coffee {
        String type;
        BigDecimal price;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Coffee)) return false;

            Coffee coffee = (Coffee) o;

            if (!price.equals(coffee.price)) return false;
            if (!type.equals(coffee.type)) return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result = type.hashCode();
            result = 31 * result + price.hashCode();
            return result;
        }

        @Override
        public String toString() {
            return "Coffee{" +
                    "type='" + type + '\'' +
                    ", price=" + price +
                    '}';
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public BigDecimal getPrice() {
            return price;
        }

        public void setPrice(BigDecimal price) {
            this.price = price;
        }


    }
}
