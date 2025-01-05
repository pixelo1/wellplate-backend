package com.pixelo.health.wellplate.authentication.infrastructure.postgresql;

import com.pixelo.health.wellplate.authentication.domain.member.AuthMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AuthMemberPostgresqlRepository extends JpaRepository<AuthMember, UUID> {

    Optional<AuthMember> findByLoginId(String login);
}
