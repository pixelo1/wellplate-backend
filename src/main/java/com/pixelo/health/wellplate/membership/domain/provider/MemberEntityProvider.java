package com.pixelo.health.wellplate.membership.domain.provider;

import com.pixelo.health.wellplate.membership.domain.Member;
import com.pixelo.health.wellplate.membership.domain.MemberType;

import java.util.UUID;

@Deprecated
public interface MemberEntityProvider {
    UUID getMemberId();
    String getLoginId();
    String getEmail();
    String getPassword();
    MemberType getMemberType();
}
