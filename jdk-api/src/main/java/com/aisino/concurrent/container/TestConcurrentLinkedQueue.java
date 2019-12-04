package com.aisino.concurrent.container;

import org.junit.Test;

import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author: xiajun003
 * @Date: 2019/8/21 14:38
 * @Description:
 * https://www.cnblogs.com/haimishasha/p/10808906.html
 *  Queue -> AbstractQueue -> ConcurrentLinkedQueue
 */
public class TestConcurrentLinkedQueue {

    // 相较于阻塞队列，没有 put/take 阻塞方法。
    private ConcurrentLinkedQueue concurrentLinkedQueue = new ConcurrentLinkedQueue();

    private ConcurrentLinkedDeque concurrentLinkedDeque = new ConcurrentLinkedDeque();
    @Test
    public void test(){
        concurrentLinkedQueue.add(2);
        concurrentLinkedDeque.add(2);

    }


}
