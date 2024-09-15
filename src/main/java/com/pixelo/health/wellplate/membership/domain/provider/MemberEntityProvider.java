package com.pixelo.health.wellplate.membership.domain.provider;

import com.pixelo.health.wellplate.membership.domain.Member;
import com.pixelo.health.wellplate.membership.domain.MemberType;

import java.util.UUID;

public interface MemberEntityProvider {
    UUID getMemberId();
    String getEmail();
    String getPassword();
    MemberType getMemberType();
}
