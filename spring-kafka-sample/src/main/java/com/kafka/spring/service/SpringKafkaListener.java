package com.kafka.spring.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.logging.Logger;

@Component
public class SpringKafkaListener {
    private static final Logger log = Logger.getLogger(SpringKafkaListener.class.getName());

    @KafkaListener(topics = "employee", groupId = "group-01", errorHandler = "customErrorHandler")
    void listenerGroup01_1(@Payload String data, @Headers Map<String, Object> headers) throws Exception {
        Long threadId = Thread.currentThread().getId();
        log.info("=========== Spring kafka Listener =================" + threadId);
        log.info("==" + threadId + " Record Value : " + data);
        if (headers != null)
            headers.forEach((k, v) -> log.info("==" + threadId + " Headers | Key : " + k + " Value : " + v));
        log.info("==" + threadId + " Thread Name : " + Thread.currentThread().getName() + " Thread ID : " + Thread.currentThread().getId());
        log.info("==================================================" + threadId);
        throw new Exception("Custom Exception ");
    }

   /* @KafkaListener(topics = "employee", groupId = "group-01")
    void listenerGroup01_2(@Payload  String data, @Headers Map<String, Object> headers) {
        Long threadId = Thread.currentThread().getId();
        log.info("=========== Spring kafka Listener =================" + threadId);
        log.info("==" + threadId + " Record Value : " + data);
        if (headers != null)
            headers.forEach((k, v) -> log.info("==" + threadId + " Headers | Key : " + k + " Value : " + v));
        log.info("==" + threadId + " Thread Name : " + Thread.currentThread().getName() + " Thread ID : " + Thread.currentThread().getId());
        log.info("==================================================" + threadId);
    }

    @KafkaListener(topics = "employee", groupId = "group-01")
    void listenerGroup01_3(@Payload String data, @Headers Map<String, Object> headers) {
        Long threadId = Thread.currentThread().getId();
        log.info("=========== Spring kafka Listener =================" + threadId);
        log.info("==" + threadId + " Record Value : " + data);
        if (headers != null)
            headers.forEach((k, v) -> log.info("==" + threadId + " Headers | Key : " + k + " Value : " + v));
        log.info("==" + threadId + " Thread Name : " + Thread.currentThread().getName() + " Thread ID : " + Thread.currentThread().getId());
        log.info("==================================================" + threadId);
    }

    @KafkaListener(topics = "employee", groupId = "group-02")
    void listenerGroup02_1(@Payload String data, @Headers Map<String, Object> headers) {
        Long threadId = Thread.currentThread().getId();
        log.info("=========== Spring kafka Listener =================" + threadId);
        log.info("==" + threadId + " Record Value : " + data);
        if (headers != null)
            headers.forEach((k, v) -> log.info("==" + threadId + " Headers | Key : " + k + " Value : " + v));
        log.info("==" + threadId + " Thread Name : " + Thread.currentThread().getName() + " Thread ID : " + Thread.currentThread().getId());
        log.info("==================================================" + threadId);
    }

    @KafkaListener(topics = "employee", groupId = "group-02")
    void listenerGroup02_2(@Payload String data, @Headers Map<String, Object> headers) {
        Long threadId = Thread.currentThread().getId();
        log.info("=========== Spring kafka Listener =================" + threadId);
        log.info("==" + threadId + " Record Value : " + data);
        if (headers != null)
            headers.forEach((k, v) -> log.info("==" + threadId + " Headers | Key : " + k + " Value : " + v));
        log.info("==" + threadId + " Thread Name : " + Thread.currentThread().getName() + " Thread ID : " + Thread.currentThread().getId());
        log.info("==================================================" + threadId);
    }
*/

}
