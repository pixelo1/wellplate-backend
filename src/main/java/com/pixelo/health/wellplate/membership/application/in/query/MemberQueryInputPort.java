package com.pixelo.health.wellplate.membership.application.in.query;

import com.pixelo.health.wellplate.membership.application.vo.MemberShipVo;

import java.util.UUID;

public interface MemberQueryInputPort {
    MemberShipVo findMemberByEmail(String email);
    MemberShipVo findMemberById(UUID memberId);
}
