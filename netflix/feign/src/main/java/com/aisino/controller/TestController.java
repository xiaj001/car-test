package com.aisino.controller;

import com.aisino.feign.LoginRep;
import com.aisino.feign.LoginReqDTO;
import com.aisino.feign.LoginService;
import com.aisino.feign.Response;
import com.aisino.springevent.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author: xiajun003
 * @Date: 2019/3/21 15:06
 * @Description:
 */
@RestController
public class TestController {

    @Autowired
    private LoginService loginService;

    /*@Value("${feign.client.config.login-service.requestInterceptors}")
    private List<String> requestInterceptors;*/

    @RequestMapping("test")
    public LoginRep test(LoginReqDTO reqDTO){
        //System.err.println(reqDTO);
        LoginReqDTO loginReqDTO = new LoginReqDTO();
        loginReqDTO.setAppId("test.zufangzi.com");
        loginReqDTO.setMt(System.currentTimeMillis());
        loginReqDTO.setUserName("BY00133");
        loginReqDTO.setPassword("111111");

        LoginReqDTO loginReqDTO1 = new LoginReqDTO();
        loginReqDTO1.setAppId("test.zufangzi.com");
        loginReqDTO1.setMt(System.currentTimeMillis());
        loginReqDTO1.setUserName("BY00133");
        loginReqDTO1.setPassword("111111");
        //loginReqDTO.setLoginReqDTO(loginReqDTO1);



        Map<String,Object> parmas = new HashMap<>();
        parmas.put("appId","test.zufangzi.com");
        parmas.put("mt",System.currentTimeMillis());
        parmas.put("userName","BY00133");
        parmas.put("password","111111");
        LinkedMultiValueMap<String ,Object> param = new LinkedMultiValueMap<>();
        for (Map.Entry<String, Object> entry : parmas.entrySet()){
            param.add(entry.getKey(),entry.getValue());
        }
        LoginRep resp = loginService.login( loginReqDTO );

        //LoginRep resp = loginService.login2("test.zufangzi.com",System.currentTimeMillis(),"BY00133","111111");
        return resp;
    }

    @Autowired
    UserService userService;
    @RequestMapping("/register")
    public String register(){
        userService.register("kirito");
        return "success";
    }
}
