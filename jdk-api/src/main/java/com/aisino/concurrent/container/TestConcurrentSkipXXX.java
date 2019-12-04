package com.aisino.concurrent.container;

import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.ConcurrentSkipListSet;

/**
 * @author: xiajun003
 * @Date: 2019/8/22 12:36
 * @Description:
 */
public class TestConcurrentSkipXXX {

    private ConcurrentSkipListMap<String,String> skipMap = new ConcurrentSkipListMap<>();

    private ConcurrentSkipListSet<String> skipListSet = new ConcurrentSkipListSet();


    public void testSkipMap(){
        skipMap.put("A","aValue");


    }

    public void testSkipListSet(){


    }
}
