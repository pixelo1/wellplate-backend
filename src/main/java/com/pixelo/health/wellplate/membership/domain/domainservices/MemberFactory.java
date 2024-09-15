package com.pixelo.health.wellplate.membership.domain.domainservices;

import com.pixelo.health.wellplate.membership.domain.Member;
import com.pixelo.health.wellplate.membership.domain.domainservices.dtos.CreateMemberDto;
import org.springframework.stereotype.Component;

@Component
public class MemberFactory {

    public static Member createMember(CreateMemberDto dto) {
        return Member.builder()
                .email(dto.email())
                .password(dto.password())
                .build();
    }
}
