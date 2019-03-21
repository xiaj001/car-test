package com.aisino.intercepter;

import com.aisino.util.SignUtils;
import feign.RequestInterceptor;
import feign.RequestTemplate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author: xiajun003
 * @Date: 2019/3/21 16:30
 * @Description:
 */
public class FeignBasicAuthRequestInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {
        Map<String, Collection<String>> params = requestTemplate.queries();
        String signCode = SignUtils.sign(params, "ZYPIKel02f8u1LrKAj+bkw==");
        requestTemplate.query("signCode",signCode);
        System.err.println("interceptor start ");
    }
}
