package com.pixelo.health.wellplate.authentication.application.out;

public interface MemberShipOutputPort {

    RegisteredUserDetailsResponse registerMemberCommand(RegisterMemberRequest command);
}
