package com.aisino.concurrent.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * @author: xiajun003
 * @Date: 2019/6/26 16:16
 * @Description:
 */
public class TestCallable {


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Callable callable = new Callable() {
            @Override
            public Object call() throws Exception {
                return 2;
            }
        };

        FutureTask futureTask = new FutureTask(callable);

        Thread t = new Thread(futureTask);

        t.start();

        Object o = futureTask.get();

        System.err.println(o);
    }
}
