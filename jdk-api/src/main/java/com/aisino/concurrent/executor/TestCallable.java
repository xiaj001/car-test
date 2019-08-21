package com.aisino.concurrent.executor;

import java.util.concurrent.*;

/**
 * @author: xiajun003
 * @Date: 2019/6/26 16:16
 * @Description:
 */
public class TestCallable {


    public static void main(String[] args) throws ExecutionException, InterruptedException {

        Callable callable = new Callable() {
            int i = 0 ;
            @Override
            public Object call() throws Exception {
                System.err.println(Thread.currentThread()+"  start ");
                TimeUnit.SECONDS.sleep(2000);
                System.err.println(Thread.currentThread() + " end ");
                return 2;
            }
        };

        FutureTask futureTask = new FutureTask(callable);
        Integer res = null;
        FutureTask ft = new FutureTask(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },res);

        Thread tt = new Thread(ft);

        tt.start();
        System.err.println("before sleep");

        Object o1 = ft.get();

        System.err.println(o1);

        if (true){
            return;
        }

        Thread t = new Thread(futureTask);

        t.start();
        System.err.println("before sleep");
        TimeUnit.SECONDS.sleep(1);
        System.err.println("end sleep ");
        //t.interrupt();
        // futureTask.cancel(false);
        Object o = futureTask.get();

        System.err.println(o);
    }
}
