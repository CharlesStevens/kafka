package com.kafka_stream.channel;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface BoundChannel {

	@Input("employeechannel")
	SubscribableChannel employeechannel1();

	@Output("employeechannel")
	MessageChannel employee_channel();

}
