package com.example.kafkatest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class KafkaMessagePublisher {

    @Value("${app.topic-name}")
    public String topicName;

    @Autowired
    private KafkaTemplate<String, String> template;
    @Autowired
    private KafkaTemplate<String, UserPayload> templateUserPayload;

    @Autowired
    private KafkaTemplate<String, String> bookingKafkaTemplate;

    public void publish(String message) {
        String key = message;
        CompletableFuture<SendResult<String, String>> future = template.send(topicName, key, message);
        future.whenComplete((result, ex) -> {
            if (ex == null) {
                System.out.println("Published Message with offset=" + result.getRecordMetadata()
                        .offset() + " In partition=" + result.getRecordMetadata()
                        .partition());
            } else {
                System.out.println("Exception when publish:" + ex.getMessage());
            }
        });
    }

    public void publish(UserPayload payload) {
        CompletableFuture<SendResult<String, UserPayload>> future = templateUserPayload.send(topicName, String.valueOf(payload.id()), payload);
        future.whenComplete((result, ex) -> {
            if (ex == null) {
                System.out.println("Published Message with offset=" + result.getRecordMetadata()
                        .offset() + " In partition=" + result.getRecordMetadata()
                        .partition());
            } else {
                System.out.println("Exception when publish:" + ex.getMessage());
            }
        });
    }

    private static void handeleFuture(CompletableFuture<SendResult<String, String>> future) {
        future.whenComplete((result, ex) -> {
            if (ex == null) {
                System.out.println("Published Message with offset=" + result.getRecordMetadata()
                        .offset() + " In partition=" + result.getRecordMetadata()
                        .partition());
            } else {
                System.out.println("Exception when Booking:" + ex.getMessage());
            }
        });
    }
}
