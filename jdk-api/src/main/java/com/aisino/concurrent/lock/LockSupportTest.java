package com.aisino.concurrent.lock;


import java.util.concurrent.locks.LockSupport;

/**
 * @author: xiajun003
 * @Date: 2018/12/20 16:19
 * @Description:
 */
public class LockSupportTest {

    public static void main(String[] args) {
        LockSupportTest lockSupportTest = new LockSupportTest();
        lockSupportTest.testPart();
    }

    public void testPart(){
        LockSupport.park();
        LockSupport.unpark(Thread.currentThread());
        System.err.println(666);
    }
}
