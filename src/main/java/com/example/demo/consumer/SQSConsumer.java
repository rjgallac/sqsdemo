package com.example.demo.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class SQSConsumer{

    private static final Logger LOGGER = LoggerFactory.getLogger(SQSConsumer.class);

    @SqsListener(value = "sample-queue")
    public void receiveMessage(Map<String, Object> message) {
        LOGGER.info("SQS Message Received : {}", message);
    }
}
