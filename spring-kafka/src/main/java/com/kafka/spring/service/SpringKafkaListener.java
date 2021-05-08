package com.kafka.spring.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.logging.Logger;

@Component
public class SpringKafkaListener {
    private static final Logger log = Logger.getLogger(SpringKafkaListener.class.getName());

    @KafkaListener(topics = "employee")
    void listener(@Payload String data, @Header(KafkaHeaders.MESSAGE_KEY) String messageKey, @Header(KafkaHeaders.OFFSET) int offset,
                  @Header(KafkaHeaders.PARTITION_ID) int partitionId, @Header(KafkaHeaders.TOPIC) String topic, @Headers Map<String, String> headers) {
        log.info("=========== Spring kafka Listener =================");
        log.info("== Partition Number : " + partitionId);
        log.info("== Record Key : " + messageKey);
        log.info("== Record Topic : " + topic);
        log.info("== Record Value : " + data);
        if (headers != null) headers.forEach((k, v) -> log.info("== Headers | Key : " + k + " Value : " + v));
        log.info("== Thread Name : " + Thread.currentThread().getName() + " Thread ID : " + Thread.currentThread().getId());
        log.info("==================================================");
    }

}
