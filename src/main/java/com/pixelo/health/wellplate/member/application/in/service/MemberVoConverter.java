package com.pixelo.health.wellplate.member.application.in.service;

import com.pixelo.health.wellplate.member.application.in.vo.MemberVo;
import com.pixelo.health.wellplate.member.domain.Member;
import org.springframework.stereotype.Component;

@Component
public class MemberVoConverter {

    public MemberVo toMemberVo(Member member) {
        return MemberVo.builder()
                .memberId(member.memberId())
                .build();
    }
}
