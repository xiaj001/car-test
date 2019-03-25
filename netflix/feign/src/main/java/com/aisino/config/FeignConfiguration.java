package com.aisino.config;

import com.aisino.feign.MeDecoder;
import com.aisino.intercepter.FeignBasicAuthRequestInterceptor;
import feign.Logger;
import feign.codec.Decoder;
import feign.optionals.OptionalDecoder;
import org.springframework.cloud.openfeign.support.ResponseEntityDecoder;
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
     * 日志级别
     * @return
     */
    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    /**
     * 创建Feign请求拦截器，在发送请求前设置认证的token,各个微服务将token设置到环境变量中来达到通用
     * @return
     */
    @Bean
    public FeignBasicAuthRequestInterceptor basicAuthRequestInterceptor() {
        return new FeignBasicAuthRequestInterceptor();
    }

    @Bean
    public Decoder feignDecoder(){
        return new OptionalDecoder(new ResponseEntityDecoder(new MeDecoder()));
    }


}
