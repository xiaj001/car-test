package com.aisino.concurrent.thread;

import org.junit.Test;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author: xiajun003
 * @Date: 2019/8/27 18:34
 * @Description:
 */
public class TestThreadPool {

    @Test
    public void tst() throws InterruptedException {
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(1, 10, 1, TimeUnit.SECONDS, new ArrayBlockingQueue<>(3));
        for (int i = 0 ; i < 10 ; i ++){
            threadPool.execute(new Task(i));
        }

        TimeUnit.SECONDS.sleep(15);
        System.err.println(111);

        for (int i = 0 ; i < 10 ; i ++){
            threadPool.execute(new Task(i));
        }
        System.err.println(222);
        TimeUnit.SECONDS.sleep(15);

    }


    class Task implements Runnable{
        int i;
        Task(int i){
           this.i = i;
        }
        @Override
        public void run() {
            try {
                System.err.println(Thread.currentThread().getName()+":"+i);
                TimeUnit.SECONDS.sleep(1);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
