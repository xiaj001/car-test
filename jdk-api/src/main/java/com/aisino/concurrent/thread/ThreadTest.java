package com.aisino.concurrent.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author: xiajun003
 * @Date: 2019/7/18 14:47
 * @Description:
 */
public class ThreadTest {

    /**
     *
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {

        ThreadTest test = new ThreadTest();
        test.testJoin();

    }

    ExecutorService executors = Executors.newFixedThreadPool(10);
    /**
     * 现在有 T1、T2、T3 三个线程，怎样保证 T2 在 T1执行完毕后执行
     *  T3 在 T2执行完毕后执行
     * @throws InterruptedException
     */
    public void testJoin() throws InterruptedException {

        Thread t1 = new Thread(new MyThread("t1",10000));
        /*Thread t2 = new Thread(new MyThread("t2",2000));
        Thread t3 = new Thread(new MyThread("t3",1000));*/
        t1.start();
        t1.join();

        System.err.println(666);
       /* t2.start();
        t2.join();
        t3.start();*/
    }


    static class MyThread implements Runnable{

        String name;
        long time;

        MyThread(String name,long time){
            this.name = name;
            this.time = time;
        }
        @Override
        public void run() {
            System.err.println(name+" start");
            try {
                Thread.sleep(time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.err.println(name+" end");
        }
    }
}
