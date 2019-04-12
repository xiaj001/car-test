package com.aisino.config;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author: xiajun003
 * @Date: 2019/4/3 17:10
 * @Description:
 */
@Component
public class FeignFormMessageConverter implements HttpMessageConverter {


    @Override
    public boolean canRead(Class clazz, MediaType mediaType) {
        return false;
    }

    @Override
    public boolean canWrite(Class clazz, MediaType mediaType) {
        return mediaType!=null && mediaType.equals(MediaType.APPLICATION_FORM_URLENCODED) ;
    }

    @Override
    public List<MediaType> getSupportedMediaTypes() {
        List<MediaType> list = new ArrayList<>();
        list.add(MediaType.APPLICATION_FORM_URLENCODED);
        return list;
    }

    @Override
    public Object read(Class clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        return null;
    }

    @Override
    public void write(Object object, MediaType contentType, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        FormHttpMessageConverter formHttpMessageConverter = new FormHttpMessageConverter();
        LinkedMultiValueMap map = new LinkedMultiValueMap();
        String param = JSONObject.toJSONString(object);
        Map<String, String> mapParam = JSONObject.parseObject(param, new TypeReference<Map<String, String>>(){});
        for (String key : mapParam.keySet()){
            map.add(key,mapParam.get(key));
        }
        formHttpMessageConverter.write(map,MediaType.APPLICATION_FORM_URLENCODED,outputMessage);
    }
}
