package com.kafka_stream.server;

import com.kafka_stream.channel.BoundChannel;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.ComponentScan;

import java.util.logging.Logger;


@SpringBootApplication
@ComponentScan(basePackages = "com.kafka_stream")
@EnableBinding(BoundChannel.class)
public class SpringKafkaStreamServer {
    private static final Logger log = Logger.getLogger(SpringKafkaStreamServer.class.getName());

    public static void main(String[] args) {
        log.info("Starting SpringKafkaStreamServer....");
        new SpringApplicationBuilder(SpringKafkaStreamServer.class).web(WebApplicationType.SERVLET).run(args);
    }
}

