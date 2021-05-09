package com.kafka.spring.controller;

import com.kafka.spring.service.SpringKafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
public class SpringKafkaController {
    private static final Logger log = Logger.getLogger(SpringKafkaController.class.getName());
    @Autowired
    private SpringKafkaProducer producerService;

    @PostMapping("producer/produceMessage")
    public void startProducing(@RequestBody String message, @RequestParam("topic") String topic) {
        log.info("produce message called for topic : " + topic);
        producerService.send(topic, message);
    }

}
