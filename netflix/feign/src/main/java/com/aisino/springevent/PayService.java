package com.aisino.springevent;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

/**
 * @author: xiajun003
 * @Date: 2019/5/21 12:49
 * @Description:
 */
@Service
public class PayService implements ApplicationListener {
    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        System.err.println(66666666);
    }
}
