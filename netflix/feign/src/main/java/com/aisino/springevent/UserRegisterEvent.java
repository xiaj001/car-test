package com.aisino.springevent;

import org.springframework.context.ApplicationEvent;

/**
 * @author: xiajun003
 * @Date: 2019/5/21 11:00
 * @Description:
 */
public class UserRegisterEvent extends ApplicationEvent {

    public UserRegisterEvent(String source) {
        super(source);
    }
}
