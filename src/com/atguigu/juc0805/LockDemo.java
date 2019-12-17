package com.atguigu.juc0805;


import java.util.concurrent.TimeUnit;

class Phone{
    public synchronized void sendEmail() throws InterruptedException {
        TimeUnit.SECONDS.sleep(4);
    }
    public synchronized void sendSMS()
    {

    }

}



public class LockDemo {
}
