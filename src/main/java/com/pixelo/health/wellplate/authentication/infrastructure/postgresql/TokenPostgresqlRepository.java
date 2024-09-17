package com.pixelo.health.wellplate.authentication.infrastructure.postgresql;

import com.pixelo.health.wellplate.authentication.domain.token.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TokenPostgresqlRepository extends JpaRepository<Token, UUID> {

    List<Token> findAllValidTokenByMemberIdAndRevokedAndExpired(UUID memberId, boolean revoked, boolean expired);

    Optional<Token> findByToken(String token);
}
