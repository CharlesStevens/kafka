package com.kafka.spring.handlers;

import org.apache.kafka.clients.consumer.Consumer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.listener.KafkaListenerErrorHandler;
import org.springframework.kafka.listener.ListenerExecutionFailedException;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component("customErrorHandler")
//@Qualifier("customErrorHandler")
public class KafkaCustomErrorHandler implements KafkaListenerErrorHandler {
    private static final Logger log = Logger.getLogger(KafkaCustomErrorHandler.class.getName());

    @Override
    public Object handleError(Message<?> message, ListenerExecutionFailedException e) {
        log.info("======================= Custom Error handler Invoked (No Consumer) ==================");
        log.info("== message payload : " + message.getPayload());
        log.info("== printStackTrace ==");
        e.printStackTrace();
        log.info("====");
        log.info("=========================================");
        return message;
    }

    @Override
    public Object handleError(Message<?> message, ListenerExecutionFailedException exception, Consumer<?, ?> consumer) {
        log.info("======================= Custom Error handler Invoked ( With Consumer) ==================");
        log.info("== message payload : " + message.getPayload());
        log.info("== printStackTrace ==");
        exception.printStackTrace();
        log.info("====");
        log.info("=========================================");
        return message;
    }
}
