package com.pixelo.health.wellplate.membership.infrastructure.postgresql;

import com.pixelo.health.wellplate.membership.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MemberPostgresqlRepository extends JpaRepository<Member, UUID> {
}
