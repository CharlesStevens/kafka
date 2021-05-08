package com.kafka.producer.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.Header;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Component
public class KafkaProducerService {
    private static final Logger log = Logger.getLogger(KafkaProducerService.class.getName());

    @Autowired
    private Producer<String, String> producer;

    public void produce(String topic, String content) throws JsonProcessingException {
        List<Header> headers = new ArrayList<>();
        headers.add(new RecordHeader("abhishek", "sharma".getBytes()));

        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(content);

        String message = node.get("message").asText();
        String key = node.get("key") == null ? null : node.get("key").asText();


        final ProducerRecord<String, String> producerRecord = key == null ? new ProducerRecord<String, String>(topic, null, null, message, headers) :
                new ProducerRecord<String, String>(topic, null, key, message, headers);
        producer.send(producerRecord);

        log.info("Message sent to producer topic : " + topic);
        log.info("===============MESSAGE============");
        if (key != null) log.info("Hash of Key : " + key.hashCode());
        log.info(message);
        log.info("=================END============");

    }

    @PreDestroy
    public void destroy() {
        producer.close();
        log.info("=================PRODUCER CLOSED============");
    }
}
