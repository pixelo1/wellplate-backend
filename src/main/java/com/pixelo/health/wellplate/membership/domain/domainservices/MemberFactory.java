package com.pixelo.health.wellplate.membership.domain.domainservices;

import com.pixelo.health.wellplate.membership.domain.Member;
import com.pixelo.health.wellplate.membership.domain.MemberType;
import com.pixelo.health.wellplate.membership.domain.domainservices.dtos.CreateMemberDto;
import org.springframework.stereotype.Component;

//todo 아직 factory 패턴을 사용할만한 이유가 없음
@Component
@Deprecated
public class MemberFactory {

    public static Member createMemberTypeOfWellnessChallenger(CreateMemberDto dto) {
        return Member.builder()
                .loginId(dto.loginId())
                .email(dto.email())
                .password(dto.password())
                .memberType(MemberType.ROLE_WELLNESS_CHALLENGER)
                .build();
    }
}
