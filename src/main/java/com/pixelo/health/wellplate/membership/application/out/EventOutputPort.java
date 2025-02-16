package com.pixelo.health.wellplate.membership.application.out;

import com.pixelo.health.wellplate.membership.application.out.dto.ExternalEventDto;
import com.pixelo.health.wellplate.membership.infrastructure.event.kafka.KafkaEventType;
import com.pixelo.health.wellplate.membership.infrastructure.event.kafka.KafkaTopic;

public interface EventOutputPort {

    void internalPublish(Object event);

    void externalPublish(ExternalEventDto<Object> event);
}
