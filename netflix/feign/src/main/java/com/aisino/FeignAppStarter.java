package com.aisino;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author: xiajun003
 * @Date: 2019/3/21 12:00
 * @Description:
 */
@EnableFeignClients
@SpringBootApplication
public class FeignAppStarter {

    public static void main(String[] args) {
        SpringApplication.run(FeignAppStarter.class,args);
    }
}
