package com.pixelo.health.wellplate.core.auth;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    //todo email -> PIID 로 (pii 거쳐서 email 검색 piId 리턴 -> piId가 있는 memberId )
}
