package com.pixelo.health.wellplate.membership.infrastructure.event.outbox;

import com.pixelo.health.wellplate.membership.infrastructure.postgresql.MemberShipOutboxEventPostgresqlRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class MembershipOutboxEventService {

    private final MemberShipOutboxEventPostgresqlRepository memberShipOutBoxRepository;

    public void saveEvent(MembershipOutboxEvent memberShipOutboxEvent) {
        memberShipOutBoxRepository.save(memberShipOutboxEvent);
        log.info("Event saved in outbox: {}", memberShipOutboxEvent);
    }
}
