package com.aisino.elasticsearch;

import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author: xiajun003
 * @Date: 2019/7/30 17:48
 * @Description:
 *
 * TransportClient 使用9300端口通信，数据序列化为二进制。理论上来说，效率更高，但是官方发现实际上相差无几
 *      并且由于版本迭代而进行的兼容导致其优势远不及带来的缺点，所以其将被RestClient取代。
 *      没有连接池、使用这个Api会导致代码结构混乱。
 *
 *  https://www.jianshu.com/p/c1f2161a5d22
 *  RestClient 是对原生Rest接口的封装，使用9200端口，http协议进行通信。
 *      针对可用节点，进行负载均衡。对错误和故障，进行故障转移。持久连接，日志追踪，原子操作。
 *
 */
public class TransportClientTest {

    public static void main(String[] args) throws UnknownHostException {
        //此步骤添加IP，至少一个，如果设置了"client.transport.sniff"= true 一个就够了，因为添加了自动嗅探配置
        TransportClient client = new PreBuiltTransportClient(Settings.EMPTY)
                .addTransportAddress(new TransportAddress(InetAddress.getByName("host1"), 9300))
                .addTransportAddress(new TransportAddress(InetAddress.getByName("host2"), 9300));

        GetRequest getRequest = new GetRequest();
        GetResponse getResponse = client.get(getRequest).actionGet();
        // on shutdown  关闭client
        String id = getResponse.getId();
        System.err.println(getResponse);
        client.close();
    }
}
