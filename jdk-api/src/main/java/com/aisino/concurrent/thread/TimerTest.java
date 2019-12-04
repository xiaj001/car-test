package com.aisino.concurrent.thread;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @author: xiajun003
 * @Date: 2019/8/1 15:57
 * @Description:
 */
public class TimerTest {

    public static void main(String[] args) {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {

            }
        };
    }
}
