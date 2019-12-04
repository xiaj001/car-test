package com.aisino.collection;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: xiajun003
 * @Date: 2019/8/30 11:32
 * @Description:
 */
public class TestMap {


    @Test
    public void test(){
        Map<String,String> m = new HashMap<>();
        m.put("fdfafdfd1","fdlfdfojdm");
        m.put("lfddmfidofdfdfd2","fdfdfdsvcv");
        m.put("avcfgfgfbmokb;lmmbfd3","uhnsfdyh");


        for(Map.Entry<String, String> entry : m.entrySet()) {
            System.err.println("key:"+entry.getKey()+",value:"+entry.getValue());

        }
    }
}
