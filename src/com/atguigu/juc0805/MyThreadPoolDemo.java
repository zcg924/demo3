package com.atguigu.juc0805;

import java.util.concurrent.*;

public class MyThreadPoolDemo {
    public static void main(String[] args) {
        System.out.println("************");
//         ExecutorService threadPool = Executors.newFixedThreadPool(5);
//         ExecutorService threadPool = Executors.newCachedThreadPool();
//         ExecutorService threadPool = Executors.newSingleThreadExecutor();
        ExecutorService threadPool = new ThreadPoolExecutor(
                2,
                5,
                1L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(3),
                Executors.defaultThreadFactory(),new ThreadPoolExecutor.AbortPolicy());
        try {
            for (int i = 1; i <=8 ; i++) {
                threadPool.execute(()->{System.out.println(Thread.currentThread().getName() + "/t 办理业务");});
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            threadPool.shutdown();
        }
    }
}
