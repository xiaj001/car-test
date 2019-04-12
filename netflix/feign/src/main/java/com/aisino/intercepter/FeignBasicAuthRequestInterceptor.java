package com.aisino.intercepter;

import com.aisino.util.SignUtils;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.util.CollectionUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author: xiajun003
 * @Date: 2019/3/21 16:30
 * @Description:
 */
public class FeignBasicAuthRequestInterceptor implements RequestInterceptor {

    private static final Pattern QUERY_PATTERN = Pattern.compile("([^&=]+)(=?)([^&]+)?");

    @Override
    public void apply(RequestTemplate requestTemplate) {
        Map<String, List<String>> queryParam = getQueryParam(new String(requestTemplate.body()));
        String signCode = SignUtils.sign(queryParam, "ZYPIKel02f8u1LrKAj+bkw==");
        requestTemplate.query("signCode",signCode);
        System.err.println("interceptor start ");
    }


    private Map<String, List<String>> getQueryParam(String s){
        Map<String, List<String>> queryParams = new HashMap() ;
        Matcher matcher = QUERY_PATTERN.matcher(s);
        while(matcher.find()) {
            String name = decodeQueryParam(matcher.group(1));
            String eq = matcher.group(2);
            String value = matcher.group(3);
            value = value != null ? decodeQueryParam(value) : (StringUtils.hasLength(eq) ? "" : null);
            List<String> list = queryParams.get(name) == null ? new ArrayList<String>() : queryParams.get(name);
            list.add(value);
            queryParams.put(name,list);
        }

        return queryParams;
    }

    private  String decodeQueryParam(String value) {
        try {
            return URLDecoder.decode(value, "UTF-8");
        } catch (UnsupportedEncodingException var3) {

            return URLDecoder.decode(value);
        }
    }
}
