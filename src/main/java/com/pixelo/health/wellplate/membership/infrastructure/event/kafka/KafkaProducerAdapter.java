package com.pixelo.health.wellplate.membership.infrastructure.event.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class KafkaProducerAdapter implements KafkaProducer {

    public static final String EVENT_TYPE = "EVENT_TYPE";
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    @Override
    public void sendMessage(KafkaTopic topic, KafkaEventType kafkaEventType, Object payload) {
//        var messageJson = jsonParser(payload);

        Message<Object> message = MessageBuilder
                .withPayload(payload)
                .setHeader(KafkaHeaders.TOPIC, topic.code())
                .setHeader(EVENT_TYPE, kafkaEventType.code())
                .build();

//        var messageJson = jsonParser(message);
        log.debug("sending message: {}", message);

        kafkaTemplate.send(message);

    }

    private String jsonParser(Object payload) {
        try{
            return objectMapper.writeValueAsString(payload);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("메시지를 변환하는데 실패했습니다");
        }
    }
}
