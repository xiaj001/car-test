package com.aisino.feign;

import com.alibaba.fastjson.JSONObject;
import feign.FeignException;
import feign.Response;
import feign.Util;
import feign.codec.DecodeException;
import feign.codec.Decoder;
import org.springframework.util.StopWatch;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * @author: xiajun003
 * @Date: 2019/3/21 19:49
 * @Description:
 */
public class MeDecoder implements Decoder {

    @Override
    public Object decode(Response response, Type type) throws IOException, DecodeException, FeignException {
        StopWatch stopWatch = new StopWatch("test");

        Response.Body body = response.body();
        if (body == null) {
            return null;
        }
        String res = Util.toString(body.asReader());

        JSONObject jsonObject = JSONObject.parseObject(res);
        if(!jsonObject.getLong("errorCode").equals(200000L)){
            throw new RuntimeException(jsonObject.getString("msg"));
        }
        String data = jsonObject.getString("data");
        return JSONObject.parseObject(data,type);
    }
}
