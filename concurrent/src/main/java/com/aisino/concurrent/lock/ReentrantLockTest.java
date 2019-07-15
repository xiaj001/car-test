package com.aisino.concurrent.lock;

import org.junit.jupiter.api.Test;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: xiajun003
 * @Date: 2018/12/20 16:25
 * @Description:
 */
public class ReentrantLockTest {

    @Test
    public void testLock(){
        Lock lock = new ReentrantLock();
        lock.lock();

        System.err.println(6666);
        lock.unlock();
    }
}
