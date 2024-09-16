package com.pixelo.health.wellplate.membership.infrastructure.springrest.internal.callee;

import com.pixelo.health.wellplate.membership.MemberFacade;
import com.pixelo.health.wellplate.membership.MemberShipFacadeVo;
import com.pixelo.health.wellplate.membership.RegisterMemberFacadeCommand;
import com.pixelo.health.wellplate.membership.application.in.command.MemberInputPort;
import com.pixelo.health.wellplate.membership.application.in.command.RegisterMemberCommand;
import com.pixelo.health.wellplate.membership.application.vo.MemberShipVo;
import com.pixelo.health.wellplate.membership.infrastructure.springrest.internal.MemberFacadeMapStruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class MemberCalleeInternalAdapter implements MemberFacade {

    private final MemberInputPort memberInputPort;
    private final MemberFacadeMapStruct memberFacadeMapStruct;

    @Override
    public MemberShipFacadeVo registerMember(RegisterMemberFacadeCommand commandFacade) {
        var command = RegisterMemberCommand.builder()
                .email(commandFacade.email())
                .password(commandFacade.password())
                .build();
        MemberShipVo memberShipVo = memberInputPort.registerMemberCommand(command);
        return memberFacadeMapStruct.toMemberShipFacadeVo(memberShipVo);
    }
}
