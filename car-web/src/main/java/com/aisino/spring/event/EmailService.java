package com.aisino.spring.event;

import org.springframework.context.ApplicationListener;

/**
 * @author: xiajun003
 * @Date: 2019/5/21 11:03
 * @Description:
 */
public class EmailService implements ApplicationListener<UserRegisterEvent> {

    @Override
    public void onApplicationEvent(UserRegisterEvent userRegisterEvent) {
        System.out.println("邮件服务接到通知，给 " + userRegisterEvent.getSource() + " 发送邮件...");

    }
}
