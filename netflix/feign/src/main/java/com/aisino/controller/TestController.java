package com.aisino.controller;

import com.aisino.feign.LoginRep;
import com.aisino.feign.LoginService;
import com.aisino.feign.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: xiajun003
 * @Date: 2019/3/21 15:06
 * @Description:
 */
@RestController
public class TestController {

    @Autowired
    private LoginService loginService;

    @RequestMapping("test")
    public LoginRep test(){
        LoginRep resp = loginService.login("test.zufangzi.com", "4db4a895b2b325d8db9bb33a1ed5bbbb02eae53fd6345dd68c9ef18a87fc5f09", System.currentTimeMillis(), "BY00133", "111111");
        return resp;
    }
}
