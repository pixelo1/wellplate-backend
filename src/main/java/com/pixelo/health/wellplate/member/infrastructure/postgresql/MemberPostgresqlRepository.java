package com.pixelo.health.wellplate.member.infrastructure.postgresql;

import com.pixelo.health.wellplate.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MemberPostgresqlRepository extends JpaRepository<Member, UUID> {
}
