package com.aisino.netfix.ribbon;

import com.netflix.client.AbstractLoadBalancerAwareClient;
import com.netflix.client.ClientRequest;
import com.netflix.client.IResponse;
import com.netflix.client.RequestSpecificRetryHandler;
import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.ILoadBalancer;

/**
 * @author: xiajun003
 * @Date: 2019/12/4 22:06
 * @Description:
 */
public class MyRestClient extends AbstractLoadBalancerAwareClient {

    public MyRestClient(ILoadBalancer lb) {
        super(lb);
    }

    @Override
    public RequestSpecificRetryHandler getRequestSpecificRetryHandler(ClientRequest request, IClientConfig requestConfig) {
        return null;
    }

    @Override
    public IResponse execute(ClientRequest clientRequest, IClientConfig iClientConfig) throws Exception {
        return null;
    }
}
