package com.aisino.mq.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;
import java.util.Random;

public class KafkaProducerTest {
    public static void main(String[] args){
        Properties props = new Properties();
        props.put("bootstrap.servers", "10.33.108.39:9092");
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        Producer<String, String> producer = new KafkaProducer<String, String>(props);
        for (int i = 0; i < 100; i++){
            Random random = new Random();
            int i1 = random.nextInt(100);
            System.err.println(i1/10);
            producer.send(new ProducerRecord<String, String>("test",0, Integer.toString(i), Integer.toString(i)));
        }


        producer.close();

    }
}
