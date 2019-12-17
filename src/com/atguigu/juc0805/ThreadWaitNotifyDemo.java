package com.atguigu.juc0805;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadWaitNotifyDemo {
    /**
     * 题目：现在两个线程，可以操作初始值为零的一个变量，
     * 实现一个线程对该变量加1，一个线程对该变量减1
     * 实现交替，来10轮，变量初始值为零
     *
     * 1.高聚低合前提下，线程操作资源类
     * 2.判断/干活/通知
     * 3.防止多线程通信时，虚假唤醒的bug，在wait用while
     *
     */
    public static void main(String[] args) {

        AirConditioner airConditioner = new AirConditioner();
        new Thread(()-> {
            for (int i = 1; i <= 10; i++) {
                try {
                    airConditioner.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"A").start();

        new Thread(()-> {
            for (int i = 1; i <= 10; i++) {
                try {
                    airConditioner.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"B").start();

        new Thread(()-> {
            for (int i = 1; i <= 10; i++) {
                try {
                    airConditioner.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"C").start();

        new Thread(()-> {
            for (int i = 1; i <= 10; i++) {
                try {
                    airConditioner.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"D").start();
    }


}

class AirConditioner
{
    private  int number = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void increment() throws InterruptedException{
        lock.lock();
        try {
            while (number != 0) {
                condition.await();
            }
            ++number;
            System.out.println(Thread.currentThread().getName() + "\t" + number);
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
        public void decrement()throws InterruptedException{
            lock.lock();
            try {
                while (number == 0){
                    condition.await();
                }
                --number;
                System.out.println(Thread.currentThread().getName()+"\t" + number);
                condition.signalAll();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
    }
    /*public synchronized void increment() throws InterruptedException {
        while(number != 0 ){
            this.wait();
        }
        ++number;
        System.out.println(Thread.currentThread().getName()+"\t"+number);
        this.notifyAll();
    }
    public synchronized void decrement() throws InterruptedException {
        while(number == 0 ){
            this.wait();
        }
        --number;
        System.out.println(Thread.currentThread().getName()+"\t"+number);
        this.notifyAll();
    }*/

}

