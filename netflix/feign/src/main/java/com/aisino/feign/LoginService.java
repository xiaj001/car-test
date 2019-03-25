package com.aisino.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author: xiajun003
 * @Date: 2018/11/30 15:24
 * @Description:
 */
@FeignClient(name = "login-service",url = "http://offline.openapi.zufangzi.com")
public interface LoginService {

    @RequestMapping(method = RequestMethod.POST,value = "/user/login")
    LoginRep login(@RequestParam("appId") String appId,
                             @RequestParam("signCode") String signCode,
                             @RequestParam("mt") Long mt,
                             @RequestParam("userName") String userName,
                             @RequestParam("password") String password);

    @RequestMapping(method = RequestMethod.POST,value = "user/verifyToken")
    String verifyToken();
}
