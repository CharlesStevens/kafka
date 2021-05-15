package com.kafka_stream.controller;

import com.kafka_stream.channel.MessageProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
public class SpringKafkaController {
    private static final Logger log = Logger.getLogger(SpringKafkaController.class.getName());
    @Autowired
    private MessageProducer producerService;

    @PostMapping("producer/produceMessage")
    public void startProducing(@RequestBody String message) {
        log.info("produce message called for topic " + message);
        producerService.sendJsonMetaMessage(message);
    }

}
