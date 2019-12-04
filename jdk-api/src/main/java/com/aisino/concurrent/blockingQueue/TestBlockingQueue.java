package com.aisino.concurrent.blockingQueue;

import org.junit.Test;

import java.util.Comparator;
import java.util.concurrent.*;

/**
 * @author: xiajun003
 * @Date: 2019/8/21 14:35
 * @Description:
 * https://www.cnblogs.com/haimishasha/p/10808906.html
 */
public class TestBlockingQueue {

    // 一个lock，两个 condition
    private ArrayBlockingQueue arrayBlockingQueue = new ArrayBlockingQueue(2);

    // 两个lock，两个condition
    private LinkedBlockingQueue linkedBlockingQueue = new LinkedBlockingQueue();

    private LinkedBlockingDeque linkedBlockingDeque = new LinkedBlockingDeque();

    private PriorityBlockingQueue priorityBlockingQueue = new PriorityBlockingQueue();

    // https://blog.csdn.net/qq_38293564/article/details/80591441
    private DelayQueue delayQueue = new DelayQueue();

    private  SynchronousQueue synchronousQueue = new SynchronousQueue();

    private LinkedTransferQueue linkedTransferQueue = new LinkedTransferQueue();


    @Test
    public void testArrayBlockingQueue() throws InterruptedException {
        arrayBlockingQueue.add(2);
        arrayBlockingQueue.add(2);
        arrayBlockingQueue.add(2);
    }

    @Test
    public void testPriorityBlockingQueue(){


        // 优先队列只保证队首元素是有序的，不保证整个队列在某一时刻有序。
        // 如果构造函数没有传 Comparator 参数，则元素对象必须实现Comparable接口，否则报 ClassCastException 异常

        priorityBlockingQueue = new PriorityBlockingQueue(2,new Comparator(){

            @Override
            public int compare(Object o1, Object o2) {
                return 0;
            }
        });

        priorityBlockingQueue.add(new Object());
        priorityBlockingQueue.add(new Object());
        priorityBlockingQueue.add(new Object());
        System.err.println("over !");
    }

    @Test
    public void testLinkedBlockingQueue(){

    }

    @Test
    public void testDelayQueue(){
        delayQueue.add(new Delayed() {
            // 获取还有多长时间到期
            @Override
            public long getDelay(TimeUnit unit) {
                return unit.toDays(1);
            }

            // 将最先到期的元素放到队首
            @Override
            public int compareTo(Delayed o) {
                return 0;
            }
        });
    }

    public void testSynchronousQueue(){
        synchronousQueue.add(new Object());
        synchronousQueue.offer(new Object());
    }

    public void testLinkedTransferQueue(){
        linkedTransferQueue.offer(new Object());
    }
}
