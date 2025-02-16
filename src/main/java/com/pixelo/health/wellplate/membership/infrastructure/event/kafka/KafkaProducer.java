package com.pixelo.health.wellplate.membership.infrastructure.event.kafka;

public interface KafkaProducer {

    void sendMessage(KafkaTopic topic, KafkaEventType kafkaEventType, Object message);
}
