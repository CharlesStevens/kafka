package com.kafka.consumer.server;

import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
public class ConsumerConfiguration {

    @Bean
    public KafkaConsumer<String, String> init() {
        Properties props = new Properties();
        props.put("bootstrap.servers", "box1:9092,box2:9092,box3:9092");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("auto.offset.reset","latest");
        props.put("enable.auto.commit","false");
        props.put("group.id", "group-02");
        props.put("key.deserializer",
                "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer",
                "org.apache.kafka.common.serialization.StringDeserializer");
        return new KafkaConsumer<String, String>(props);
    }
}
