package com.aisino.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author: xiajun003
 * @Date: 2019/1/3 18:53
 * @Description:
 */
@Component
public class BeanUtils implements ApplicationContextAware{

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
        TestConfig bean = applicationContext.getBean(TestConfig.class);
        System.err.println(bean);
    }
}
