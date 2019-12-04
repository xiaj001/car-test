package com.aisino.concurrent.tools;

import org.junit.Test;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author: xiajun003
 * @Date: 2019/8/27 11:06
 * @Description:
 */
public class TestSemaphore {


    @Test
    public void test() throws InterruptedException {
        Semaphore semaphore = new Semaphore(2);

        for (int i = 0; i < 10 ; i ++){
            new Thread(() -> {
                try {

                    semaphore.acquire();
                    System.err.println(Thread.currentThread()+":start");
                    TimeUnit.SECONDS.sleep(1);
                    System.err.println(Thread.currentThread()+":end");
                    semaphore.release();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }

        Object wait = new Object();
        synchronized (wait){
            wait.wait();
        }

    }
}
