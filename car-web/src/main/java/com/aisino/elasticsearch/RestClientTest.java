package com.aisino.elasticsearch;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.client.Request;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import java.io.IOException;

/**
 * @author: xiajun003
 * @Date: 2019/7/30 17:49
 * @Description:
 */
public class RestClientTest {

    public static void main(String[] args) throws IOException {
        RestClientBuilder builder = RestClient.builder(
                new HttpHost("localhost", 9200, "http")
        );

        builder.setDefaultHeaders(new Header[]{new BasicHeader("token","tokenVal")});
        builder.setMaxRetryTimeoutMillis(1000);
        RestClient restClient = builder.build();

        Request request = new Request("POST","/index");
        request.setJsonEntity("");
        Response response = restClient.performRequest(request);
        System.err.println(response.getStatusLine().getStatusCode());
        String body = EntityUtils.toString(response.getEntity());
        System.err.println(body);
    }
}
