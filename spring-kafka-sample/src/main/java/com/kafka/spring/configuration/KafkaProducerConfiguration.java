package com.kafka.spring.configuration;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProducerConfiguration {

    @Value("${sample.spring.kafka.bootstrapservers}")
    String bootstrapServer;

    @Bean
    public Map<String, Object> producerConfigProperties() {
        final Map<String, Object> producerConfiguration = new HashMap<>();
        producerConfiguration.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
        producerConfiguration.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        producerConfiguration.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        return producerConfiguration;
    }

    @Bean
    public ProducerFactory<String, String> getProducerConfig() {
        return new DefaultKafkaProducerFactory(producerConfigProperties());
    }

    @Bean
    public KafkaTemplate getkafkaTemplate() {
        return new KafkaTemplate<String, String>(getProducerConfig());
    }
}
