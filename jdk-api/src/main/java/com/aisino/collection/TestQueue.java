package com.aisino.collection;

import org.junit.Before;
import org.junit.Test;
import java.util.LinkedList;

/**
 * @author: xiajun003
 * @Date: 2019/8/21 12:43
 * @Description:
 */
public class TestQueue {

    // Queue -> Deque -> LinkedList
    private LinkedList queue = new LinkedList();
    @Before
    public void before (){
        queue.add(1);
        queue.add(2);
        queue.add(3);
        queue.add(4);
    }

    @Test
    public void testAdd(){
        System.err.println(queue.size());
        queue.add(0);
        System.err.println(queue.size());
    }

    @Test
    public void testEle(){
        System.err.println(queue.size());
        queue.element();
        System.err.println(queue.size());
    }


}
