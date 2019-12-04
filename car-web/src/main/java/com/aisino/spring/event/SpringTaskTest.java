package com.aisino.spring.event;

import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.util.concurrent.ListenableFutureTask;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author: xiajun003
 * @Date: 2019/11/11 17:45
 * @Description:
 */
public class SpringTaskTest {


    public static void main(String[] args) throws ExecutionException, InterruptedException {

       /* FutureTask futureTask = new FutureTask(new Callable() {
            @Override
            public Object call() throws Exception {
                TimeUnit.SECONDS.sleep(2);
                return 5;
            }
        });

        new Thread( futureTask ).start();
        System.err.println(futureTask.get());*/


        ListenableFutureTask listenableFutureTask = new ListenableFutureTask(new Callable() {
            @Override
            public Object call() throws Exception {
                TimeUnit.SECONDS.sleep(5);
                return 6;
            }
        });
        listenableFutureTask.addCallback(new ListenableFutureCallback() {
            @Override
            public void onFailure(Throwable throwable) {
                System.err.println("error happen");
            }

            @Override
            public void onSuccess(Object o) {
                System.err.println("Result");
                System.err.println(o);
            }
        });

        new Thread(listenableFutureTask).start();
        System.err.println("add call back");
        System.out.println(listenableFutureTask.get());
        System.out.println(8888);
        synchronized (SpringTaskTest.class){
            SpringTaskTest.class.wait();
        }

    }
}
