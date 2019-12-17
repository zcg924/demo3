package com.atguigu.juc0805;


import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class MyCache{
    private volatile Map<String,String> map = new HashMap<>();
    ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();

    public void put(String key, String value){
        rwl.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+"\t 写入开始");
            map.put(key,value);
            System.out.println(Thread.currentThread().getName()+"\t 写入结束");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            rwl.writeLock().unlock();
        }

    }
    public void get(String key){
        rwl.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+"\t 读取开始");
            String result = map.get(key);
            System.out.println(Thread.currentThread().getName()+"\t 读取结束"+result);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            rwl.readLock().unlock();
        }

    }
    /*public void put(String key, String value){
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+"\t 写入开始");
            map.put(key,value);
            System.out.println(Thread.currentThread().getName()+"\t 写入结束");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }
    public void get(String key){
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+"\t 读取开始");
            String result = map.get(key);
            System.out.println(Thread.currentThread().getName()+"\t 读取结束"+result);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }*/
}

/**
 * 多线程同时读一个资源类没有任何问题，所以为了满足并发量，读取共享资源应该可以同时进行
 * 但是
 * 如果有一个线程想去写共享资源，就不应该有其它线程可以多这个资源进行读写
 * 小总结；
 *  读-读能共存
 *  读-写不能共存
 *  写-写不能共存
 */
public class ReadWriteLockDemo {

    public static void main(String[] args) {
        MyCache myCache = new MyCache();
        for (int i = 1; i <=10 ; i++) {
            final int tempI = i;
            new Thread(()->{
                myCache.put(tempI+"",tempI+"");
            },String.valueOf(i)).start();
        }
        for (int i = 1; i <=10 ; i++) {
            final int tempI = i;
            new Thread(()->{
                myCache.get(tempI+"");
            },String.valueOf(i)).start();
        }
    }
}
