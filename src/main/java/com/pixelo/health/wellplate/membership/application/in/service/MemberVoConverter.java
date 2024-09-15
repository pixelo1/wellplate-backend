package com.pixelo.health.wellplate.membership.application.in.service;

import com.pixelo.health.wellplate.membership.application.in.vo.MemberVo;
import com.pixelo.health.wellplate.membership.domain.Member;
import org.springframework.stereotype.Component;

@Component
public class MemberVoConverter {

    public MemberVo toMemberVo(Member member) {
        return MemberVo.builder()
                .memberId(member.memberId())
                .build();
    }
}
