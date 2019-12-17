package com.atguigu.juc0805;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class BlockingQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);

        System.out.println(blockingQueue.offer("a", 3L, TimeUnit.SECONDS));
        System.out.println(blockingQueue.offer("b", 3L, TimeUnit.SECONDS));
        System.out.println(blockingQueue.offer("c", 3L, TimeUnit.SECONDS));
//        System.out.println(blockingQueue.offer("d", 3L, TimeUnit.SECONDS));

        System.out.println(blockingQueue.poll(3L,TimeUnit.SECONDS));
        System.out.println(blockingQueue.poll(3L,TimeUnit.SECONDS));
        System.out.println(blockingQueue.poll(3L,TimeUnit.SECONDS));
        System.out.println(blockingQueue.poll(3L,TimeUnit.SECONDS));




        /*blockingQueue.put("a");
        blockingQueue.put("b");
        blockingQueue.put("c");


        blockingQueue.take();
        blockingQueue.take();
        blockingQueue.take();
        System.out.println("***********");
        blockingQueue.take();
        System.out.println("===========");
*/
      /*  System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());*/


        /*System.out.println(blockingQueue.offer("a"));
        System.out.println(blockingQueue.offer("b"));
        System.out.println(blockingQueue.offer("c"));*/
//        System.out.println(blockingQueue.offer("d"));

//        System.out.println(blockingQueue.peek());
        /*System.out.println(blockingQueue.offer("b"));
        System.out.println(blockingQueue.offer("c"));
        System.out.println(blockingQueue.offer("d"));*/




        /*System.out.println(blockingQueue.add("a"));
        System.out.println(blockingQueue.add("b"));
        System.out.println(blockingQueue.add("c"));*/

        /*System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());*/

//        System.out.println(blockingQueue.element());

    }
}
