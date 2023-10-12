package com.example.kafkatest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

//    @KafkaListener(topics = "${app.topic-name}", groupId = "${app.group-id}")
//    public void consume(String message) {
//        System.out.println("Message consumed:" + message);
//    }

    @KafkaListener(topics = "${app.topic-name}", groupId = "${app.group-id}")
    public void consumeUser(UserPayload user) {
        simulateFailure(user);
        System.out.println("Message consumed:" + user);
    }

    private void simulateFailure(UserPayload payload) {
        if (payload.name() == null || payload.name()
                .isBlank()) {
            throw new RuntimeException("Ivalid name");
        }
    }
}
