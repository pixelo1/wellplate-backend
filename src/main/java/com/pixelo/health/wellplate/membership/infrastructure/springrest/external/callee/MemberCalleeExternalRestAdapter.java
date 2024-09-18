package com.pixelo.health.wellplate.membership.infrastructure.springrest.external.callee;

import com.pixelo.health.wellplate.core.spi.AuthUser;
import com.pixelo.health.wellplate.core.spi.ResultResponse;
import com.pixelo.health.wellplate.membership.application.in.command.MemberCommandInputPort;
import com.pixelo.health.wellplate.membership.infrastructure.springrest.external.callee.request.RegisterMemberRequest;
import com.pixelo.health.wellplate.membership.infrastructure.springrest.external.callee.response.RegisteredMemberResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/members")
@Tag(name = "회원 관리 - 가입, 상세")
public class MemberCalleeExternalRestAdapter {

    private final MemberCommandInputPort memberCommandInputPort;
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
        var memberShipVo = memberCommandInputPort.registerMemberCommand(command);
        var response = memberResponseStruct.toRegisteredMemberResponse(memberShipVo.memberVo());
        return ResultResponse.ok(response);
    }

    @GetMapping
    @Operation(summary = "회원 정보 조회")
    public ResultResponse<?> test(
            @AuthenticationPrincipal
            AuthUser authUser
    ) {
        return ResultResponse.ok("");
    }
}
