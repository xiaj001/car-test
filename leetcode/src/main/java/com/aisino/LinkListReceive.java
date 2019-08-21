package com.aisino;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * @author: xiajun003
 * @Date: 2019/7/31 15:32
 * @Description:
 */
 public  class  LinkListReceive {

    public static void receiveLinkList(Node head,int k){
        if (k <= 0 ){
            return;
        }
        // 垃圾收集器
        // 串行、并行
        // 如何判断对象已经死去:
        // 根地址法，可达性分析，引用计数法
        /**
         * 1.如何判断对象已经死去
         *      引用地址法:定义、优点、缺点
         *      可达性分析
         * 2.四种引用
         * 3.垃圾收集算法
         * 4.垃圾收集器及优缺点
         * 5.
         */


    }

    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        for (int i = 1 ; i <= 1 ; i ++){
           Thread t =  new Thread(myThread);
           t.setName("i-"+i);
           t.start();
        }
        for (int j = 1 ; j <= 0 ;j ++){
            Thread t =  new Thread(myThread);
            t.setName("j-"+j);
            t.start();
        }
    }




    // 有序 二分查找法

    /**
     * 三种锁对象
     *      对象锁、类锁、静态类
     *      代码块、方法、静态方法、类
     * @param nums1
     * @param nums2
     * @throws InterruptedException
     */
    public  void merge(int[] nums1,int[] nums2) throws InterruptedException {
       wait();
       notify();

       Object lock = new Object();
       lock.wait();
       lock.notify();
    }

}

class Node {
    public int value;
    public Node next;
}


class MyThread  implements Runnable{

     private int i;

     /*public MyThread(String name){
         this.name = name;
     }*/

    @Override
    public  void run() {
        doRun();
    }


    /**
     * synchronize修饰非静态方法、同步代码块的synchronize(this)和synchronize(非this对象)
     *      的用法锁的是对象，线程想要执行对应的同步代码，需要获得对象锁
     *  synchronize修饰静态方法以及同步代码块的synchronize(类.class)用法锁是类，
     *      线程想要执行对应的同步代码，需要获得类锁。
     *
     *
     *      wait()
     */




    public  void doRun()  {
        try {
            String name =  Thread.currentThread().getName();
            Object o = new Object();
            synchronized (Object.class){
                System.err.println(Thread.currentThread() +" start " + name);
                Object.class.notify();
                //o.notify();
                TimeUnit.SECONDS.sleep(1);
                System.err.println(Thread.currentThread() +" end " + name);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


    static class Lock{}
}
