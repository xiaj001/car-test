package com.aisino.feign;

import com.aisino.config.FeignConfiguration;
import com.aisino.config.FormParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * @author: xiajun003
 * @Date: 2018/11/30 15:24
 * @Description:
 * url = "http://offline.openapi.zufangzi.com",
 */
@FeignClient(name ="login-service",url = "${feign.client.config.login-service.url}")
public interface LoginService {

    @RequestMapping(method = RequestMethod.POST,value = "/user/login",headers = "Content-Type:application/x-www-form-urlencoded"
            /*,produces =  MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE*/
    )
    LoginRep login(@RequestBody LoginReqDTO loginReqDTO);

    @RequestMapping(method = RequestMethod.POST,value = "/user/login",
            produces =  MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    LoginRep login2(
            @RequestParam("appId")String appId,
            @RequestParam("mt")Long mt,
            @RequestParam("userName")String userName,
            @RequestParam("password")String password
    );

    @RequestMapping(method = RequestMethod.POST,value = "user/verifyToken")
    String verifyToken();
}
