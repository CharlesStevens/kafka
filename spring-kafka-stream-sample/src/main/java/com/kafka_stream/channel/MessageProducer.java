package com.kafka_stream.channel;

import com.fasterxml.jackson.databind.JsonNode;
import com.kafka_stream.controller.SpringKafkaController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class MessageProducer {
  private static final Logger log = Logger.getLogger(MessageProducer.class.getName());

  @Autowired(required = false)
  @Qualifier("employeechannel")
  private MessageChannel employee_channel;

  public void sendJsonMetaMessage(String jsonMeta) {
    try {
      synchronized (this) {
        log.info("produce message called for topic " + jsonMeta);
        employee_channel.send(MessageBuilder.withPayload(jsonMeta).build());
      }
    } catch (Exception e) {
      System.out.println("Error occurred while sending json Meta : " + e.getMessage());
      e.printStackTrace();
    }
  }

}
