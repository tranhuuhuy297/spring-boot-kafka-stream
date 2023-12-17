package com.example.kafkastream.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class TestConsumer {

    @KafkaListener(topics = "user-topic", groupId = "${user.group-id}", containerFactory = "userContainerFactory")
    public void consume(String message) {
        System.out.println("message = " + message);
    }
}
