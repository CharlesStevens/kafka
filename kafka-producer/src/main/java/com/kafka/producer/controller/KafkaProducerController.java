package com.kafka.producer.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kafka.producer.service.KafkaProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
public class KafkaProducerController {
    private static final Logger log = Logger.getLogger(KafkaProducerController.class.getName());
    @Autowired
    private KafkaProducerService producerService;

    @PostMapping("producer/produceMessage")
    public void startProducing(@RequestBody String message, @RequestParam("topic") String topic) throws JsonProcessingException {
        log.info("produce message called for topic : "+ topic);
        producerService.produce(topic,message);

    }
}
