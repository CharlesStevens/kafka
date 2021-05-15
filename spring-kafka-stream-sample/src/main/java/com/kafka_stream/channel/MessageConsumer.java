package com.kafka_stream.channel;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

@Component
public class MessageConsumer {


    @StreamListener("employeechannel")
    public void jsonMessage(String jsonMessage) {
        System.out
                .println("received Json Message, through kafka stream topic : " + "xmlmessage" + " : " +
                        jsonMessage);
    }


}
