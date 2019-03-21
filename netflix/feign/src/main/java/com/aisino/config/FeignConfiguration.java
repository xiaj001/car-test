package com.aisino.config;

import com.aisino.intercepter.FeignBasicAuthRequestInterceptor;
import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: xiajun003
 * @Date: 2019/3/21 16:30
 * @Description:
 */
@Configuration
public class FeignConfiguration {

    /**
     * ��־����
     * @return
     */
    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    /**
     * ����Feign�������������ڷ�������ǰ������֤��token,����΢����token���õ��������������ﵽͨ��
     * @return
     */
    @Bean
    public FeignBasicAuthRequestInterceptor basicAuthRequestInterceptor() {
        return new FeignBasicAuthRequestInterceptor();
    }


}
