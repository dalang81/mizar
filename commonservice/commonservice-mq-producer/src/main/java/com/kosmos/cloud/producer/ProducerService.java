package com.kosmos.cloud.producer;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.SubscribableChannel;

public interface ProducerService {
	
	@Output("kosmosInput")
	SubscribableChannel sendMessage();
}
