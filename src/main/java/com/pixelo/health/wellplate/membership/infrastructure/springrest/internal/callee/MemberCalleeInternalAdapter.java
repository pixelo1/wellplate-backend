package com.pixelo.health.wellplate.membership.infrastructure.springrest.internal.callee;

import com.pixelo.health.wellplate.core.spi.ResultResponse;
import com.pixelo.health.wellplate.membership.MemberFacade;
import com.pixelo.health.wellplate.membership.MemberShipFacadeVo;
import com.pixelo.health.wellplate.membership.RegisterMemberFacadeCommand;
import com.pixelo.health.wellplate.membership.application.in.command.MemberCommandInputPort;
import com.pixelo.health.wellplate.membership.application.in.command.RegisterMemberCommand;
import com.pixelo.health.wellplate.membership.application.in.query.MemberQueryInputPort;
import com.pixelo.health.wellplate.membership.infrastructure.springrest.internal.MemberFacadeMapStruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class MemberCalleeInternalAdapter implements MemberFacade {

    private final MemberCommandInputPort memberCommandInputPort;
    private final MemberQueryInputPort memberQueryInputPort;
    private final MemberFacadeMapStruct memberFacadeMapStruct;

    @Override
    public ResultResponse<MemberShipFacadeVo> registerMember(RegisterMemberFacadeCommand commandFacade) {
        var command = RegisterMemberCommand.builder()
                .email(commandFacade.email())
                .password(commandFacade.password())
                .build();
        var memberShipVo = memberCommandInputPort.registerMemberCommand(command);
        var memberShipFacadeVo = memberFacadeMapStruct.toMemberShipFacadeVo(memberShipVo);
        return ResultResponse.ok(memberShipFacadeVo);
    }

    @Override
    public ResultResponse<MemberShipFacadeVo> findMemberByEmail(String email) {
        var memberShipVo = memberQueryInputPort.findMemberByEmail(email);
        var memberShipFacadeVo = memberFacadeMapStruct.toMemberShipFacadeVo(memberShipVo);
        return ResultResponse.ok(memberShipFacadeVo);
    }

    @Override
    public ResultResponse<MemberShipFacadeVo> findMemberById(UUID memberId) {
        var memberShipVo = memberQueryInputPort.findMemberById(memberId);
        var memberShipFacadeVo = memberFacadeMapStruct.toMemberShipFacadeVo(memberShipVo);
        return ResultResponse.ok(memberShipFacadeVo);
    }
}
