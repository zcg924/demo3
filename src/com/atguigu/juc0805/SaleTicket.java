package com.atguigu.juc0805;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SaleTicket {
    public static void main(String[] args) {
        Ticket ticket = new Ticket();
        new Thread(() -> {for (int i = 1; i < 32; i++) ticket.sale();}, "A").start();
        new Thread(() -> {for (int i = 1; i < 32; i++) ticket.sale();}, "B").start();
        new Thread(() -> {for (int i = 1; i < 32; i++) ticket.sale();}, "C").start();

}
}

class Ticket {
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


