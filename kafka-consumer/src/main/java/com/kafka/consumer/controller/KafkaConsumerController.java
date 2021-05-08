package com.kafka.consumer.controller;

import com.kafka.consumer.service.KafkaConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@RestController
public class KafkaConsumerController {
    private static final Logger log = Logger.getLogger(KafkaConsumerController.class.getName());


    @Autowired
    private KafkaConsumerService consumerService;


    @PostMapping("consumer/subscribeToTopic")
    public void subscribeToTopic(@RequestParam("topic") String topic) {
        log.info("Subscribe to topic invoked with topic : " + topic);

        consumerService.subscribeToTopic(topic);
        List<Integer> ll = Arrays.asList(1, 2, 3, 4);
    }


    @PostMapping("consumer/startPolling")
    public void startPolling() {
        log.info("Start Polling invoked ");
        consumerService.startPolling();
    }

    @PostMapping("consumer/stopPolling")
    public void stopPolling() {
        log.info("Stop Polling invoked ");
        consumerService.startPolling();
    }
}
