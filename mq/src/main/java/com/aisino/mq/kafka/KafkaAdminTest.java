package com.aisino.mq.kafka;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.DescribeClusterResult;
import org.apache.kafka.clients.admin.KafkaAdminClient;
import org.apache.kafka.clients.admin.ListTopicsResult;
import org.apache.kafka.common.KafkaFuture;

import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ExecutionException;

/**
 * @author: xiajun003
 * @Date: 2019/6/20 18:21
 * @Description:
 */
public class KafkaAdminTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Properties properties = new Properties();
        KafkaAdminClient adminClient = (KafkaAdminClient)AdminClient.create(properties);
        ListTopicsResult topics = adminClient.listTopics();
        KafkaFuture<Set<String>> names = topics.names();
        Set<String> strings = names.get();
        for (String topic:strings){
            System.err.println(topic);
        }
        DescribeClusterResult describeClusterResult = adminClient.describeCluster();
        KafkaFuture<String> stringKafkaFuture = describeClusterResult.clusterId();
        String s = stringKafkaFuture.get();
        System.err.println(s);


    }
}
