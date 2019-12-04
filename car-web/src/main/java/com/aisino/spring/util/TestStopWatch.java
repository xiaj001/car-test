package com.aisino.spring.util;

import org.springframework.util.StopWatch;

import java.util.concurrent.TimeUnit;

/**
 * @author: xiajun003
 * @Date: 2019/3/26 20:21
 * @Description:
 */
public class TestStopWatch {

    public static void main(String[] args) throws InterruptedException {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        TimeUnit.SECONDS.sleep(3);
        stopWatch.stop();
       // System.err.println(stopWatch.prettyPrint());
        System.out.println("=============================================================================");
      //  System.err.println(stopWatch.shortSummary());
        System.out.println("=============================================================================");
        System.err.println(stopWatch.getLastTaskTimeMillis());

    }
}
