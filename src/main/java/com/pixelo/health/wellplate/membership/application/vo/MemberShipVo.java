package com.pixelo.health.wellplate.membership.application.vo;

import lombok.Builder;

@Builder
public record MemberShipVo(
        MemberVo memberVo,
        UserDetailVo userDetailVo
) {
}
