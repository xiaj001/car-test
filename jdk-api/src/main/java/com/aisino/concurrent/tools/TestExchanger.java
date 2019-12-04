package com.aisino.concurrent.tools;

import org.junit.Test;

import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;

/**
 * @author: xiajun003
 * @Date: 2019/8/22 14:27
 * @Description:
 */
public class TestExchanger {


    @Test
    public void testExchanger() throws InterruptedException {
        Exchanger<String> exchanger = new Exchanger();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {


                    String a = "a";
                    System.err.println("Thread a : "+a);
                    TimeUnit.SECONDS.sleep(3);
                    String exchange = exchanger.exchange(a);
                    System.err.println("Thread a exchange : "+exchange);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    String b = "b";
                    System.err.println("Thread b : "+b);
                    TimeUnit.SECONDS.sleep(8);
                    String exchange = exchanger.exchange(b);
                    System.err.println("Thread b exchange : "+exchange);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

        TimeUnit.SECONDS.sleep(10);
    }

}
