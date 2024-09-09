package com.pixelo.health.wellplate.member.infrastructure.springrest.external.callee.response;

import com.pixelo.health.wellplate.member.application.in.vo.MemberVo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class RegisteredMemberResponse {
    private UUID memberId;

    public static RegisteredMemberResponse of(MemberVo vo) {
        return RegisteredMemberResponse.builder()
                .memberId(vo.memberId())
                .build();
    }
}
