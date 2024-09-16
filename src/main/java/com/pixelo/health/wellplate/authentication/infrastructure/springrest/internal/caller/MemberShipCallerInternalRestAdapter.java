package com.pixelo.health.wellplate.authentication.infrastructure.springrest.internal.caller;

import com.pixelo.health.wellplate.authentication.application.out.MemberShipOutputPort;
import com.pixelo.health.wellplate.authentication.application.out.RegisterMemberRequest;
import com.pixelo.health.wellplate.authentication.application.out.RegisteredUserDetailsResponse;
import com.pixelo.health.wellplate.membership.MemberFacade;
import com.pixelo.health.wellplate.membership.MemberShipFacadeVo;
import com.pixelo.health.wellplate.membership.RegisterMemberFacadeCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

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
        MemberShipFacadeVo memberShipFacadeVo = memberFacade.registerMember(commandFacade);
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
