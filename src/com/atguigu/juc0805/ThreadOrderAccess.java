package com.atguigu.juc0805;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ShareResource
{
    private int flag = 1;
    private int temp;
    private Lock lock = new ReentrantLock();
    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();
    private Condition c3 = lock.newCondition();

    public void printAll() throws InterruptedException {
        if(Thread.currentThread().getName()=="AA") {
            temp = 5;
            lock.lock();
            try {
                while (flag !=1) {
                    c1.await();
                }
                for (int i = 1; i <=temp; i++) {
                    System.out.println(Thread.currentThread().getName()+"\t"+i);
                }
                flag = 2;
                c2.signal();

            } finally {
                lock.unlock();
            }
        }
        if(Thread.currentThread().getName()=="BB") {
            temp = 10;
            lock.lock();
            try {
                while (flag !=2) {
                    c2.await();
                }
                for (int i = 1; i <=temp; i++) {
                    System.out.println(Thread.currentThread().getName()+"\t"+i);
                }
                flag = 3;
                c3.signal();

            } finally {
                lock.unlock();
            }
        }
        if (Thread.currentThread().getName()=="CC"){
            temp = 15;
            lock.lock();
            try {
                while (flag !=3) {
                    c3.await();
                }
                for (int i = 1; i <=temp; i++) {
                    System.out.println(Thread.currentThread().getName()+"\t"+i);
                }
                flag = 1;
                c1.signal();

            } finally {
                lock.unlock();
            }
        }
    }


    public void print5() throws InterruptedException {
        lock.lock();
        try {
            while (flag !=1) {
                c1.await();
            }
            for (int i = 1; i <=5; i++) {
                System.out.println(Thread.currentThread().getName()+"\t"+i);
            }
            flag = 2;
            c2.signal();

        } finally {
            lock.unlock();
        }
    }
    public void print10() throws InterruptedException {
        lock.lock();
        try {
            while (flag !=2) {
                c2.await();
            }
            for (int i = 1; i <=10; i++) {
                System.out.println(Thread.currentThread().getName()+"\t"+i);
            }
            flag = 3;
            c3.signal();

        } finally {
            lock.unlock();
        }
    }
    public void print15() throws InterruptedException {
    lock.lock();
    try {
        while (flag !=3) {
            c1.await();
        }
        for (int i = 1; i <=15; i++) {
            System.out.println(Thread.currentThread().getName()+"\t"+i);
        }
        flag = 1;
        c3.signal();

    } finally {
        lock.unlock();
    }
}

}


public class ThreadOrderAccess {
    /**
     * 多线程之间按顺序调用，实现A先干->B次之->C最后
     * AA打印5次，BB打印10次，CC打印15次
     * ...10轮
     *
     */
    public static void main(String[] args) {
        ShareResource shareResource = new ShareResource();
        new Thread(() -> {
            for (int i = 0; i <=10; i++) {
                try {
                    shareResource.printAll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"AA").start();
        new Thread(() -> {
            for (int i = 0; i <=10; i++) {
                try {
                    shareResource.printAll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"BB").start();
        new Thread(() -> {
            for (int i = 0; i <=10; i++) {
                try {
                    shareResource.printAll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"CC").start();
    }

}
