package com.pixelo.health.wellplate.membership.infrastructure.event;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pixelo.health.wellplate.membership.application.out.EventOutputPort;
import com.pixelo.health.wellplate.membership.application.out.dto.ExternalEventDto;
import com.pixelo.health.wellplate.membership.infrastructure.event.kafka.KafkaEventType;
import com.pixelo.health.wellplate.membership.infrastructure.event.kafka.KafkaTopic;
import com.pixelo.health.wellplate.membership.infrastructure.event.outbox.MembershipOutboxEvent;
import com.pixelo.health.wellplate.membership.infrastructure.event.outbox.MembershipOutboxEventService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EventProducerAdapter implements EventOutputPort {

    private final ApplicationEventPublisher publisher;
//    private final KafkaProducer kafkaProducer;
    private final MembershipOutboxEventService membershipOutboxEventService;
    private final ObjectMapper objectMapper;
    @Override
    public void internalPublish(Object event) {
        publisher.publishEvent(event);
    }

    @Override
    public void externalPublish(ExternalEventDto<Object> outboxEvent) {
//        kafkaProducer.sendMessage(topic, kafkaEventType, message);
        var jsonPayload = jsonParser(outboxEvent.payload());
        var membershipOutboxEvent = MembershipOutboxEvent.builder()
                .aggregateType(outboxEvent.aggregateType())
                .aggregateId(outboxEvent.aggregateId())
                .eventType(outboxEvent.eventType())
                .topic(outboxEvent.topic())
                .payload(jsonPayload)
                .build();
        membershipOutboxEventService.saveEvent(membershipOutboxEvent);
    }

    private String jsonParser(Object payload) {
        try{
            return objectMapper.writeValueAsString(payload);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("메시지를 변환하는데 실패했습니다");
        }
    }
}
