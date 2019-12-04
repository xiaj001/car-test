package com.aisino.spring.event;

import org.springframework.context.ApplicationEvent;

/**
 * @author: xiajun003
 * @Date: 2019/5/21 11:00
 * @Description:
 */
public class UserRegisterEvent extends ApplicationEvent {

    public UserRegisterEvent(Object source) {
        super(source);
    }
}
