package http;

import java.util.HashMap;
import java.util.Map;

/**
 * http响应请求实体
 * User: suxx
 * Date: 13-11-7
 * Time: 下午2:54
 * To change this template use File | Settings | File Templates.
 */
public class HttpReq {
    /**
     * 提交参数集合
     */
    private Map<String, String> paramMap = new HashMap<String, String>();

    /**
     * 提交报头集合
     */
    private Map<String, String> headMap = new HashMap<String, String>();

    /**
     * 请求报文体
     */
    private String requestBody = "";

    public Map<String, String> getParamMap() {
        return paramMap;
    }

    public void setParamMap(Map<String, String> paramMap) {
        this.paramMap = paramMap;
    }

    public Map<String, String> getHeadMap() {
        return headMap;
    }

    public void setHeadMap(Map<String, String> headMap) {
        this.headMap = headMap;
    }

    public String getRequestBody() {
        return requestBody;
    }

    public void setRequestBody(String requestBody) {
        this.requestBody = requestBody;
    }
}
