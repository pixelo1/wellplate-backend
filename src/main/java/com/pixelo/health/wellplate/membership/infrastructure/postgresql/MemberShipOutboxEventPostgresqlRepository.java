package com.pixelo.health.wellplate.membership.infrastructure.postgresql;

import com.pixelo.health.wellplate.membership.infrastructure.event.outbox.MembershipOutboxEvent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MemberShipOutboxEventPostgresqlRepository extends JpaRepository<MembershipOutboxEvent, UUID> {
}
