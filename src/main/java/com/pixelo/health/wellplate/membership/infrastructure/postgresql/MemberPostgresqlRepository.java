package com.pixelo.health.wellplate.membership.infrastructure.postgresql;

import com.pixelo.health.wellplate.membership.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface MemberPostgresqlRepository extends JpaRepository<Member, UUID> {
    //todo email -> PIID 로 (pii 거쳐서 email 검색 piId 리턴 -> piId가 있는 memberId )
    Optional<Member> findByEmail(String email);
}
