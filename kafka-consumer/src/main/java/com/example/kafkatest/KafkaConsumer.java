package com.example.kafkatest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.PartitionOffset;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    // Simple
    //@KafkaListener(topics = "${app.topic-name}", groupId = "${app.group-id}")
    public void consume(String message) {
        System.out.println("Message consumed:" + message);
    }

    // With partition information
//    @KafkaListener(topics = "${app.topic-name}", groupId = "${app.group-id}")
    public void listenWithHeaders(
            @Payload String message,
            @Header(KafkaHeaders.RECEIVED_PARTITION) int partition) {
        System.out.println(
                "Received Message: " + message +
                        " from partition: " + partition);
    }

    //Consuming Messages from a Specific Partition
    /**
     *
     * Since the initialOffset has been set to 0 in this listener, all the previously consumed messages from partitions 0 and 3 will be re-consumed every time this listener is initialized.
     *
     * If we don’t need to set the offset, we can use the partitions property of @TopicPartition annotation to set only the partitions without the offset:
     *
     * @KafkaListener(topicPartitions
     *   = @TopicPartition(topic = "topicName", partitions = { "0", "1" }))
     *
     *
     */
    @KafkaListener(
            topicPartitions = @TopicPartition(topic = "${app.topic-name}",
                    partitionOffsets = {
                            @PartitionOffset(partition = "0", initialOffset = "0"),
                            @PartitionOffset(partition = "3", initialOffset = "0")}))
    public void listenToPartition(
            @Payload String message,
            @Header(KafkaHeaders.RECEIVED_PARTITION) int partition) {
        System.out.println(
                "Received Message: " + message+
                        " from partition: " + partition);
    }


//    @KafkaListener(topics = "${app.topic-name}", groupId = "${app.group-id}")
//    public void consumeUser(UserPayload user) {
//        simulateFailure(user);
//        System.out.println("Message consumed:" + user);
//    }


//    private void simulateFailure(UserPayload payload) {
//        if (payload.name() == null || payload.name()
//                .isBlank()) {
//            throw new RuntimeException("Ivalid name");
//        }
//    }
}
