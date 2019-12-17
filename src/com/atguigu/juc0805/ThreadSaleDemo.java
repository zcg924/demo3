package com.atguigu.juc0805;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Ticket1 {
    private int number = 30;
    private Lock lock = new ReentrantLock();

    public void sale() {
        lock.lock();
        try {
            if (number > 0) {
                System.out.println(Thread.currentThread().getName() + "\t 卖出第：" + (number--) + "\t张，还剩：" + number + "\t 张");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}

public class ThreadSaleDemo {
    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newFixedThreadPool(3);
        Ticket1 ticket1 = new Ticket1();
        try {
            for (int i = 1; i <= 30; i++) {

                threadPool.execute(() -> {
                    ticket1.sale();
                });
            }
        }finally {
            threadPool.shutdown();
        }




    }
}
