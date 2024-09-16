package com.pixelo.health.wellplate.membership;

import lombok.Builder;

@Builder
public record MemberShipFacadeVo(
        MemberFacadeVo memberVo,
        UserDetailFacadeVo userDetailVo
) {
}
