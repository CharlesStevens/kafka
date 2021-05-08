package com.kafka.consumer.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.header.Header;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.util.Arrays;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Logger;

@Component
public class KafkaConsumerService {
    private static final Logger log = Logger.getLogger(KafkaConsumerService.class.getName());

    @Autowired
    private KafkaConsumer<String, String> consumer;

    private static AtomicBoolean IS_POLLING = new AtomicBoolean(false);

    private Thread pollerThread;
    private AtomicReference<String> topicReference = new AtomicReference<>();

    public void subscribeToTopic(String topic) {
        consumer.subscribe(Arrays.asList(topic));
        topicReference.set(topic);
        log.info("Subscribed to topic : " + topic);
    }


    public void startPolling() {
        if (!IS_POLLING.get()) {
            IS_POLLING.set(true);
            log.info("Poller Started with thread : " + Thread.currentThread().getName());
            try {
                pollerThread = new Thread(() -> {
                    while (true) {
                        ConsumerRecords<String, String> records = consumer.poll(1000);
                        if (records != null && !records.isEmpty())
                            for (ConsumerRecord<String, String> record : records) {
                                log.info("===========Kafka Message Fetcher =================");
                                log.info("== Partition Number : " + record.partition());
                                log.info("== Record Key : " + record.key());
                                log.info("== Record Topic : " + record.topic());
                                log.info("== Offset : " + record.offset());
                                log.info("== Record Value : " + record.value());
                                Iterator<Header> it = record.headers().iterator();
                                while (it.hasNext()) {
                                    Header h = it.next();
                                    System.out.println("== Header Key : " + h.key() + " value : " + String.valueOf(h.value()));
                                }

                                log.info("==================================================");
                            }

                        consumer.commitAsync();
                    }
                });

                pollerThread.start();
            } catch (Exception e) {
                e.printStackTrace();
                log.warning("Error while executing thread " + e.getMessage());
            }
        }
    }

    public void stopPolling() {
        consumer.unsubscribe();
        pollerThread.suspend();
        log.info("Poller Stopped ");
        IS_POLLING.set(false);
    }


    @PreDestroy
    public void destroy() {
        consumer.unsubscribe();
        consumer.close();
        log.info("=================CONSUMER CLOSED============");
    }

}
