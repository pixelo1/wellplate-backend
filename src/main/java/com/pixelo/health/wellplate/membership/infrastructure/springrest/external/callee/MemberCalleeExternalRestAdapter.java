package com.pixelo.health.wellplate.membership.infrastructure.springrest.external.callee;

import com.pixelo.health.wellplate.core.spi.ResultResponse;
import com.pixelo.health.wellplate.membership.application.in.command.MemberInputPort;
import com.pixelo.health.wellplate.membership.infrastructure.springrest.external.callee.request.RegisterMemberRequest;
import com.pixelo.health.wellplate.membership.infrastructure.springrest.external.callee.response.RegisteredMemberResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
@Tag(name = "회원 관리 - 가입, 상세")
public class MemberCalleeExternalRestAdapter {

    private final MemberInputPort memberInputPort;
    private final MemberResponseStruct memberResponseStruct;
    private final MemberRequestStruct memberRequestStruct;

    @PostMapping
    @Operation(summary = "회원 생성 - 일반 사용자")
    public ResultResponse<RegisteredMemberResponse> registerMember(
            @RequestBody
            @Valid
            RegisterMemberRequest request
    ) {
        var command = memberRequestStruct.toRegisterMemberCommand(request);
        var memberShipVo = memberInputPort.registerMemberCommand(command);
        var response = memberResponseStruct.toRegisteredMemberResponse(memberShipVo.memberVo());
        return ResultResponse.ok(response);
    }
}
