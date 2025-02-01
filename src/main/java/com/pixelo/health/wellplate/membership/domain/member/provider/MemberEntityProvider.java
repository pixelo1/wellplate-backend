package com.pixelo.health.wellplate.membership.domain.member.provider;

import com.pixelo.health.wellplate.membership.domain.member.MemberType;

import java.util.UUID;

@Deprecated
public interface MemberEntityProvider {
    UUID getMemberId();
    String getLoginId();
    String getEmail();
    String getPassword();
    MemberType getMemberType();
}
