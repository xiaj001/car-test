package com.aisino.config;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import feign.RequestTemplate;
import feign.codec.EncodeException;
import feign.codec.Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import java.lang.reflect.Type;
import java.util.*;

/**
 * @author: xiajun003
 * @Date: 2019/4/3 19:09
 * @Description:
 */
public class MyEncoder implements Encoder {

    @Autowired
    private SpringEncoder springEncoder;

    @Override
    public void encode(Object o, Type type, RequestTemplate requestTemplate) throws EncodeException {
        if (o.getClass().getAnnotation(FormParam.class) != null){
            String param = JSONObject.toJSONString(o);
            Map<String, String> map = JSONObject.parseObject(param, new TypeReference<Map<String, String>>() {});
            for (Map.Entry<String, String> entry : map.entrySet()){
                requestTemplate.query(entry.getKey(),entry.getValue());
            }
        }else {
            springEncoder.encode(o,type,requestTemplate);
        }
    }


}
