package com.aisino.concurrent.lock;

import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author: xiajun003
 * @Date: 2018/12/20 16:25
 * @Description:
 *
 * AQS使用一个int类型的成员变量state来表示同步状态，
 * 当state>0时表示已经获取了锁，当state = 0时表示释放了锁。
 * 它提供了三个方法（getState()、setState(int newState)、
 * compareAndSetState(int expect,int update)）来对同步状态state进行操作，
 * 当然AQS可以确保对state的操作是安全的
 */
public class ReentrantLockTest {

    static ReentrantLock l = new ReentrantLock();



    @Test
    public void testLock(){

        ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
        ReentrantReadWriteLock.ReadLock readLock = readWriteLock.readLock();
        readLock.lock();
        readLock.unlock();
        ReentrantReadWriteLock.WriteLock writeLock = readWriteLock.writeLock();
        writeLock.lock();
        writeLock.unlock();
        Lock lock = new ReentrantLock();
        lock.lock();
        ((ReentrantLock) lock).getHoldCount();

        System.err.println(6666);
        lock.unlock();
    }

    public static void main(String[] args) {
        MyRunable run = new MyRunable();
        for (int i = 0 ; i < 2 ; i ++){
            Thread t = new Thread(run);
            t.start();
        }

    }


    static class MyRunable implements Runnable{

        @Override
        public void run() {
            try {
                l.lock();
                System.err.println(Thread.currentThread() + " start ");
                TimeUnit.SECONDS.sleep(100);
                System.err.println(Thread.currentThread() + " end ");
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                l.unlock();
            }

        }
    }
}
