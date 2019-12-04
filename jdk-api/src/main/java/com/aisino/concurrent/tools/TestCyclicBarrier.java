package com.aisino.concurrent.tools;

import org.junit.Test;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * @author: xiajun003
 * @Date: 2019/8/27 10:51
 * @Description:
 */
public class TestCyclicBarrier {

    @Test
    public void test() throws InterruptedException {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3);
        for (int i =0 ; i < 2; i++){
            new Thread(() -> {
                try {
                    System.err.println("enter");
                    TimeUnit.SECONDS.sleep(3);
                    System.err.println("wait");
                    cyclicBarrier.await();
                    System.err.println("over");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }

        TimeUnit.SECONDS.sleep(5);
    }
}
