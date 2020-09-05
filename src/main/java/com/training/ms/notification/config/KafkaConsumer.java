package com.training.ms.notification.config;

import org.apache.kafka.clients.consumer.ConsumerConfig;

import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.training.ms.notification.model.Event;

import java.util.HashMap;

import java.util.Map;

@Configuration
public class KafkaConsumer {

    private static final String KAFKA_BROKER = "localhost:9092";

    private static final String GROUP_ID = "kafka-sandbox";

    @Bean
    public ConsumerFactory<String, Event> consumerFactory() {
    	JsonDeserializer<Event> jsonDeserializer=new JsonDeserializer<>(Event.class,false);
    	jsonDeserializer.addTrustedPackages("*");
        return new DefaultKafkaConsumerFactory<>(consumerConfigurations(),new StringDeserializer(),
        		jsonDeserializer);
    }
    @Bean
    public Map<String, Object> consumerConfigurations() {

        Map<String, Object> configurations = new HashMap<>();


        configurations.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, KAFKA_BROKER);

        configurations.put(ConsumerConfig.GROUP_ID_CONFIG, GROUP_ID);

        configurations.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        configurations.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        return configurations;
    }
    @Bean
    ConcurrentKafkaListenerContainerFactory<String, Event> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, Event> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }
}