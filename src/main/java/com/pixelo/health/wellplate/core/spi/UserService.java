package com.pixelo.health.wellplate.core.spi;

import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.UUID;

public interface UserService extends UserDetailsService {
    //todo email -> PIID 로 (pii 거쳐서 email 검색 piId 리턴 -> piId가 있는 memberId )

    JwtUserDetails findUserById(UUID memberId);
}
