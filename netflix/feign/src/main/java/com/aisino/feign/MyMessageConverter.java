package com.aisino.feign;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.IOException;

/**
 * @author: xiajun003
 * @Date: 2019/3/21 18:31
 * @Description:
 */
public class MyMessageConverter extends AbstractHttpMessageConverter<Response> {

    @Override
    protected boolean supports(Class<?> clazz) {
        boolean annotation = clazz.isAnnotation();
        return annotation;
    }

    @Override
    protected Response readInternal(Class<? extends Response> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        return null;
    }

    @Override
    protected void writeInternal(Response response, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {

    }
}
