package com.aisino.util;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;


/**
 * @author xiajun003
 * @Date: 2018/11/28 17:28
 * @Description:
 */
public class SignUtils {

    private static final Logger log = LoggerFactory.getLogger(SignUtils.class);


    public static String sign(Map<String, Collection<String>> paramMap, String appkey)  {
        if (paramMap == null || StringUtils.isBlank(appkey)){
            throw new IllegalArgumentException("sign param error !");
        }
        List<String> keys = new ArrayList<>();
        for (String paramName : paramMap.keySet()){
            if (paramName.equals("signCode")) continue;
            keys.add(paramName);
        }
        Collections.sort(keys);
        StringBuilder sb = new StringBuilder();
        for(String key : keys){
            sb.append(key);
            if (!paramMap.get(key).isEmpty()){
                sb.append(paramMap.get(key).iterator().next());
            }
        }
        sb.append(appkey);
        log.info("[未加密原值：{}]",sb.toString());
        return DigestUtils.sha256Hex(sb.toString());
    }
}
