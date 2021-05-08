package com.kafka.producer.server;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;

import java.util.logging.Logger;

@SpringBootApplication
@ComponentScan(basePackages = "com.kafka.producer")
public class KafkaProducerServer {
    private static final Logger log = Logger.getLogger(KafkaProducerServer.class.getName());


    public static void main(String[] args) {
        log.info("Starting KafkaProducerServer....");
        new SpringApplicationBuilder(KafkaProducerServer.class).web(WebApplicationType.SERVLET).run(args);
    }
}
