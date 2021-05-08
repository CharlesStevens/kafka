package com.kafka.spring.configuration;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaTopicBuilder {
    @Value("${sample.spring.kafka.bootstrapservers}")
    String bootstrapServer;

    @Bean
    public NewTopic kafkaTopic() {
        return TopicBuilder.name("employee").build();
    }

    @Bean
    public KafkaAdmin admin(){
        Map<String,Object> configs=  new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG,bootstrapServer);
        return new KafkaAdmin(configs);
    }
}
