package com.pixelo.health.wellplate.authentication.infrastructure.springrest.internal.caller;

import com.pixelo.health.wellplate.authentication.application.out.MemberShipOutputPort;
import com.pixelo.health.wellplate.authentication.application.out.RegisterMemberRequest;
import com.pixelo.health.wellplate.authentication.application.out.RegisteredUserDetailsResponse;
import com.pixelo.health.wellplate.membership.MemberFacade;
import com.pixelo.health.wellplate.membership.MemberShipFacadeVo;
import com.pixelo.health.wellplate.membership.RegisterMemberFacadeCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;

import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class MemberShipCallerInternalRestAdapter implements MemberShipOutputPort {

    private final MemberFacade memberFacade;

    @Override
    public RegisteredUserDetailsResponse registerMemberCommand(RegisterMemberRequest command) {
        var commandFacade = RegisterMemberFacadeCommand.builder()
                .email(command.email())
                .password(command.password())
                .build();
        var memberShipFacadeVoResultResponse = memberFacade.registerMember(commandFacade);
        if (ObjectUtils.isEmpty(memberShipFacadeVoResultResponse.data())) {
            throw new IllegalArgumentException("회원 등록중 실패했습니다. email: " + command.email());
        }
        return createUserDetailsResponse(memberShipFacadeVoResultResponse.data());
    }

    @Override
    public RegisteredUserDetailsResponse findMemberByEmail(String email) {
        var memberShipFacadeVoResultResponse = memberFacade.findMemberByEmail(email);

        if (ObjectUtils.isEmpty(memberShipFacadeVoResultResponse.data())) {
            throw new IllegalArgumentException("회원을 조회하지 못했습니다. email: " + email);
        }
        return createUserDetailsResponse(memberShipFacadeVoResultResponse.data());
    }

    @Override
    public RegisteredUserDetailsResponse findMemberByMemberId(UUID memberId) {
        var memberShipFacadeVoResultResponse = memberFacade.findMemberById(memberId);

        if (ObjectUtils.isEmpty(memberShipFacadeVoResultResponse.data())) {
            throw new IllegalArgumentException("회원을 조회하지 못했습니다. wellnessChallengerId: " + memberId);
        }
        return createUserDetailsResponse(memberShipFacadeVoResultResponse.data());
    }

    private static RegisteredUserDetailsResponse createUserDetailsResponse(MemberShipFacadeVo memberShipFacadeVo) {
        return RegisteredUserDetailsResponse.builder()
                .memberId(memberShipFacadeVo.memberVo().memberId())
                .password(memberShipFacadeVo.userDetailVo().password())
                .username(memberShipFacadeVo.userDetailVo().username())
                .accountNonExpired(memberShipFacadeVo.userDetailVo().accountNonExpired())
                .accountNonLocked(memberShipFacadeVo.userDetailVo().accountNonLocked())
                .credentialsNonExpired(memberShipFacadeVo.userDetailVo().credentialsNonExpired())
                .enabled(memberShipFacadeVo.userDetailVo().enabled())
                .authorities(memberShipFacadeVo.userDetailVo().authorities())
                .build();
    }
}
