package com.aisino.agent.demo;

import java.lang.instrument.Instrumentation;

/**
 * @author: xiajun003
 * @Date: 2019/6/10 16:29
 * @Description:
 */
public class MyAgent {

    public static void premain(String args, Instrumentation ins) {
        System.out.printf("this is an agent.");
        System.out.printf("args:"+args);
    }
}
