package com.kafka.consumer.server;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;

import java.util.logging.Logger;

@SpringBootApplication
@ComponentScan(basePackages = "com.kafka.consumer")
public class KafkaConsumerServer {

    private static final Logger log = Logger.getLogger(KafkaConsumerServer.class.getName());

    public static void main(String[] args) {
        log.info("Starting KafkaConsumerServer....");
        new SpringApplicationBuilder(KafkaConsumerServer.class).web(WebApplicationType.SERVLET).run(args);
    }

}