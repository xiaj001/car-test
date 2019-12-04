package com.aisino.concurrent.container;

import org.junit.Test;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author: xiajun003
 * @Date: 2019/8/21 14:39
 * @Description:
 */
public class TestCopyOnWriteXXX {

    // 适用于读操作远远多于写操作，并且数据量较小的情况。
    // 修改容器的代价是昂贵的，因此建议批量增加addAll、批量删除removeAll。
    private CopyOnWriteArrayList list = new CopyOnWriteArrayList();

    private CopyOnWriteArraySet set = new CopyOnWriteArraySet();

    @Test
    public void test(){
        list.add(1);

    }

}
