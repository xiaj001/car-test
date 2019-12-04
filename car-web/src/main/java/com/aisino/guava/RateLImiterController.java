package com.aisino.guava;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.util.concurrent.RateLimiter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;


/**
 * @author: xiajun003
 * @Date: 2018/12/18 16:48
 * @Description:
 */
@RestController
public class RateLImiterController {

    /**
     * 指定每秒放两个令牌
     */
    RateLimiter ratelimiter = RateLimiter.create(2);

    @RequestMapping("ratelimiter")
    public String testGuavaRateLimiter(){
        System.err.println(ratelimiter.getRate());
        if (ratelimiter.tryAcquire()){
            return "rate limiter get a lock";
        }else {
            return "not get lock";
        }


    }



    @RequestMapping("cache")
    public String testGuavaCache(String arg){
        Cache<String , String> cache = CacheBuilder.newBuilder()
                .expireAfterWrite(1, TimeUnit.SECONDS)
                .maximumSize(1000).recordStats()
                .removalListener(notification -> {

                }).build();

        return null;
    }
}
