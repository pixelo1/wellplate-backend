package com.pixelo.health.wellplate.member.infrastructure.springrest.external.callee;

import com.pixelo.health.wellplate.core.spi.ResultResponse;
import com.pixelo.health.wellplate.member.application.in.command.MemberInputPort;
import com.pixelo.health.wellplate.member.application.in.command.RegisterMemberCommand;
import com.pixelo.health.wellplate.member.application.in.vo.MemberVo;
import com.pixelo.health.wellplate.member.infrastructure.springrest.external.callee.request.RegisterMemberRequest;
import com.pixelo.health.wellplate.member.infrastructure.springrest.external.callee.response.RegisteredMemberResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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

    @PostMapping
    @Operation(summary = "회원가입")
    public ResultResponse<RegisteredMemberResponse> test(
            @RequestBody
            RegisterMemberRequest request
    ) {
        var command = RegisterMemberCommand.builder()
                .email(request.getEmail())
                .password(request.getPassword())
                .build();
        var memberVo = memberInputPort.registerMemberCommand(command);
        var response = RegisteredMemberResponse.of(memberVo);
        return ResultResponse.ok(response);
    }
}
