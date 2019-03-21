package com.aisino.controller;

import com.aisino.feign.LoginService;
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
    public String test(){
        String str = loginService.login("test.zufangzi.com", "4db4a895b2b325d8db9bb33a1ed5bbbb02eae53fd6345dd68c9ef18a87fc5f09", 1543565095714L, "BY00133", "111111");
        return str;
    }
}
