package com.tool.kafka.config;

import org.apache.kafka.clients.consumer.ConsumerConfig;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
public class KafkaConsumerConfig {

    private static final Logger LOGGER = Logger.getLogger(KafkaConsumerConfig.class);

    @Value(value = "${kafka.consumer.bootstrap-servers}")
    private String bootstrapServersConfig;
    @Value(value = "${kafka.consumer.group-id}")
    private String groupIdConfig;
    @Value(value = "${kafka.consumer.key-deserializer}")
    private String keyDeserializerClassConfig;
    @Value(value = "${kafka.consumer.value-deserializer}")
    private String valueDeserializerClassConfig;
    @Value(value = "${kafka.consumer.auto-offset-reset}")
    private String autoOffsetReset;
    @Bean
    public ConsumerFactory<String, String> consumerFactory() {
        LOGGER.info("Consumer config ....");
        Map<String, Object> config = new HashMap<>();
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,bootstrapServersConfig);
        config.put(ConsumerConfig.GROUP_ID_CONFIG, groupIdConfig);
        config.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,autoOffsetReset);
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, keyDeserializerClassConfig);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,valueDeserializerClassConfig );
        return new DefaultKafkaConsumerFactory<>(config);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }
}
