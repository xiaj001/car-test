package com.aisino.concurrent.tools;

import org.junit.Test;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author: xiajun003
 * @Date: 2019/8/22 14:27
 * @Description:
 */
public class TestCountDownLantch {


    /**
     * 使用方法一、作为一个完成信号
     */
    @Test
    public void testCountDownLantch() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(10);

        System.err.println("start ");
        for (int i =0 ; i < 10 ; i ++){
            new Thread(() -> {
                try {
                    Random random = new Random(2000);
                    TimeUnit.MILLISECONDS.sleep(random.nextInt());
                    System.err.println(Thread.currentThread() + "over");
                    countDownLatch.countDown();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }).start();
        }
        System.err.println("线程全部启动完毕");
        countDownLatch.await();
        System.err.println("线程全部执行完毕");
        System.err.println("end ");
    }



    /**
     * 使用方法二、作为一个开关/入口
     */
    @Test
    public void testCountDownLantch2() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1);

        System.err.println("start ");
        for (int i =0 ; i < 10 ; i ++){
            new Thread(() -> {
                try {
                    System.err.println(Thread.currentThread() + "prepare");
                    countDownLatch.await();
                    System.err.println(Thread.currentThread() + "start");
                    Random random = new Random(2000);
                    TimeUnit.MILLISECONDS.sleep(random.nextInt());
                    System.err.println(Thread.currentThread() + "over");
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }).start();
        }
        TimeUnit.SECONDS.sleep(3);
        System.err.println("线程全部就绪完毕");
        countDownLatch.countDown();
        System.err.println("线程全部开始执行");
        TimeUnit.SECONDS.sleep(3);
        System.err.println("end ");
    }

}
