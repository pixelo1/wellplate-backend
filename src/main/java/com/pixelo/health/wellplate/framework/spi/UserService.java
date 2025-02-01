package com.pixelo.health.wellplate.framework.spi;

import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.UUID;

//todo auth bc 로 이동
public interface UserService extends UserDetailsService {
    //todo loginId -> PIID 로 (pii 거쳐서 loginId 검색 piId 리턴 -> piId가 있는 wellnessChallengerId )

    JwtUserDetails findUserById(UUID memberId);
}
