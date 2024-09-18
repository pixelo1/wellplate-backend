package com.pixelo.health.wellplate.authentication.application.out;

import java.util.UUID;

public interface MemberShipOutputPort {

    RegisteredUserDetailsResponse registerMemberCommand(RegisterMemberRequest command);
    RegisteredUserDetailsResponse findMemberByEmail(String email);

    RegisteredUserDetailsResponse findMemberByMemberId(UUID memberId);
}
