package com.pixelo.health.wellplate.membership.infrastructure.springevent;

import com.pixelo.health.wellplate.membership.application.out.InternalEventOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InternalEventSpringAdapter implements InternalEventOutputPort {

    private final ApplicationEventPublisher publisher;

    @Override
    public void publish(Object event) {
        publisher.publishEvent(event);
    }
}
