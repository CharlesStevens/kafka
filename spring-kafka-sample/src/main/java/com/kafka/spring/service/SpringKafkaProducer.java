package com.kafka.spring.service;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.logging.Logger;

@Component
public class SpringKafkaProducer {

    private static final Logger log = Logger.getLogger(SpringKafkaProducer.class.getName());

    @Autowired
    private KafkaTemplate kafkaTemplate;


    public void send(String message, String topicName) {
        ListenableFuture<SendResult<String, String>> lf = kafkaTemplate.send(topicName, message);
        log.info("Message Sent on topic : " + topicName);
        lf.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
            @Override
            public void onFailure(Throwable throwable) {
                log.info("Message send failure for topic : " + topicName + " Message : " + message);
                throwable.printStackTrace();
            }

            @Override
            public void onSuccess(SendResult<String, String> sendResult) {
                ProducerRecord<String, String> pr = sendResult.getProducerRecord();
                log.info("=========== Spring kafka Producer =================");
                log.info("==ProducerRecord | Partition Number : " + pr.partition());
                log.info("==ProducerRecord | Record Key : " + pr.key());
                log.info("==ProducerRecord | Record Topic : " + pr.topic());
                log.info("==ProducerRecord | Headers : " + pr.headers());
                log.info("==ProducerRecord | Record Value : " + pr.value());

                RecordMetadata md = sendResult.getRecordMetadata();
                log.info("RecordMetadata | Offset : " + md.offset());
                log.info("RecordMetadata | partition : " + md.partition());
                log.info("==================================================");
            }
        });

    }
}
