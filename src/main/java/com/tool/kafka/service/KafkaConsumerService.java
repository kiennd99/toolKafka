package com.tool.kafka.service;

import com.tool.kafka.util.KafkaToolConfigParams;
import com.tool.kafka.util.PropsUtil;
import org.apache.log4j.Logger;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    private static final Logger LOGGER = Logger.getLogger(KafkaConsumerService.class);

    @KafkaListener(topics ="${kafka.topics}", groupId= "${kafka.consumer.group-id}")
    public void consumeListener(String message) {
        LOGGER.info("Received message ....");
        LOGGER.info("Insert to Identity Server...");

        System.out.println("Consumed message: " + message);
    }
}
