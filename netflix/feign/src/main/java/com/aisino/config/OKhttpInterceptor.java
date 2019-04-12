package com.aisino.config;

import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import okio.Buffer;
import okio.BufferedSource;

import java.io.EOFException;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @author: xiajun003
 * @Date: 2019/4/11 11:09
 * @Description:
 */
@Slf4j
public class OKhttpInterceptor implements Interceptor {

    @Override public Response intercept(Interceptor.Chain chain) throws IOException {
        Request request = chain.request();
        HttpUrl url = request.url();
        URL url1 = url.url();
        URI uri = url.uri();

        Map<String,Object> requestInfo = new HashMap<>();
        requestInfo.put("url",request.url().url());
        requestInfo.put("method",request.method());
        requestInfo.put("headers",request.headers() == null ? null : request.headers().toMultimap());
        requestInfo.put("parameter",parseParameter(request));

        log.info("requestInfo :{}",requestInfo);
        long t1 = System.currentTimeMillis();

        Response response = chain.proceed(request);
        Map<String,Object> responseInfo = new HashMap<>();
        responseInfo.put("headers",response.headers() == null?null:response.headers().toMultimap());
        responseInfo.put("body",readResponseStr(response));
        responseInfo.put("cost",System.currentTimeMillis() - t1);

        log.info("responseInfo :{}",responseInfo);

        return response;
    }

    /**
     * 解析请求参数
     * @param request
     * @return
     */
    public static Map<String, String> parseParameter(Request request) {
        String method = request.method();
        Map<String, String> params = null;
        if ("GET".equals(method)) {
            params = parseGetParameter(request);
        } else if ("POST".equals(method) || "PUT".equals(method) || "DELETE".equals(method) || "PATCH".equals(method)) {
            RequestBody body = request.body();
            params = parseFormParameter(request);
            if (body != null && body instanceof FormBody) {
                params = parseFormParameter(request);
            }
        }
        return params;
    }

    /**
     * 获取get方式的请求参数
     * @param request
     * @return
     */
    private static Map<String, String> parseGetParameter(Request request) {
        Map<String, String> params = null;
        HttpUrl url = request.url();
        Set<String> strings = url.queryParameterNames();
        if (strings != null) {
            Iterator<String> iterator = strings.iterator();
            params = new HashMap<>();
            int i = 0;
            while (iterator.hasNext()) {
                String name = iterator.next();
                String value = url.queryParameterValue(i);
                params.put(name, value);
                i++;
            }
        }
        return params;
    }



    /**
     * 获取表单的请求参数
     * @param request
     * @return
     */
    private static Map<String, String> parseFormParameter(Request request) {

        RequestBody requestBody = request.body();
        MediaType contentType = requestBody.contentType();
        Buffer buffer = new Buffer();
        try {
            requestBody.writeTo(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String bufferString = buffer.readString(Charset.forName("UTF-8"));

        //解析 url 部分


        //如果是 json 直接返回 String
        if (contentType != null && "json".equals(contentType.subtype())) {

        }else if (contentType != null && "json".equals(contentType.subtype())){

        }else {

        }

        //如果是 form 解析

        Map<String, String> params = null;
        FormBody body = null;
        try {
            body = (FormBody) request.body();
        } catch (ClassCastException c) {
        }
        if (body != null) {
            int size = body.size();
            if (size > 0) {
                params = new HashMap<>();
                for (int i = 0; i < size; i++) {
                    params.put(body.name(i), body.value(i));
                }
            }
        }
        return params;
    }

    /**
     * 读取Response返回String内容
     * @param response
     * @return
     */
    private String readResponseStr(Response response) {
        ResponseBody body = response.body();
        BufferedSource source = body.source();
        try {
            source.request(Long.MAX_VALUE);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        MediaType contentType = body.contentType();
        Charset charset = Charset.forName("UTF-8");
        if (contentType != null) {
            charset = contentType.charset(charset);
        }
        String s = null;
        Buffer buffer = source.buffer();
        if (isPlaintext(buffer)) {
            s = buffer.clone().readString(charset);
        }
        return s;
    }


    static boolean isPlaintext(Buffer buffer) {
        try {
            Buffer prefix = new Buffer();
            long byteCount = buffer.size() < 64 ? buffer.size() : 64;
            buffer.copyTo(prefix, 0, byteCount);
            for (int i = 0; i < 16; i++) {
                if (prefix.exhausted()) {
                    break;
                }
                int codePoint = prefix.readUtf8CodePoint();
                if (Character.isISOControl(codePoint) && !Character.isWhitespace(codePoint)) {
                    return false;
                }
            }
            return true;
        } catch (EOFException e) {
            return false;
        }
    }
}
