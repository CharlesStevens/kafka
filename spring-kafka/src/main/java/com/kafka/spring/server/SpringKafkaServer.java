package com.kafka.spring.server;

import java.util.logging.Logger;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan(basePackages = "com.kafka.spring")
public class SpringKafkaServer {
    private static final Logger log = Logger.getLogger(SpringKafkaServer.class.getName());

    public static void main(String[] args) {
        log.info("Starting SpringKafkaServer....");
        new SpringApplicationBuilder(SpringKafkaServer.class).web(WebApplicationType.SERVLET).run(args);
    }
}

