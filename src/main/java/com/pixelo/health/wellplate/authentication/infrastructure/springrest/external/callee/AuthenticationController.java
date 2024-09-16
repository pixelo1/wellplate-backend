package com.pixelo.health.wellplate.authentication.infrastructure.springrest.external.callee;

import com.pixelo.health.wellplate.authentication.application.in.command.AuthenticationInputPort;
import com.pixelo.health.wellplate.authentication.application.in.command.RegisterTokenAndMemberCommand;
import com.pixelo.health.wellplate.authentication.application.vo.TokenVo;
import com.pixelo.health.wellplate.authentication.infrastructure.springrest.external.callee.request.AuthenticateMemberRequest;
import com.pixelo.health.wellplate.authentication.infrastructure.springrest.external.callee.request.RegisterTokenAndMemberRequest;
import com.pixelo.health.wellplate.authentication.infrastructure.springrest.external.callee.response.AuthenticationResponse;
import com.pixelo.health.wellplate.core.spi.ResultResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationInputPort authenticationInputPort;
    private final AuthenticationRequestMapStruct authenticationRequestMapStruct;
    private final AuthenticationResponseMapStruct authenticationResponseMapStruct;

    @PostMapping("/register")
    @Schema(title = "회원가입 요청, 토큰 발급")
    public ResultResponse<AuthenticationResponse> registerTokenAndMember(
            @RequestBody
            RegisterTokenAndMemberRequest request
    ) {
        var command = authenticationRequestMapStruct.toRegisterTokenAndMemberCommand(request);
        var tokenVo = authenticationInputPort.registerTokenAndMember(command);
        var response = authenticationResponseMapStruct.toAuthenticationResponse(tokenVo);
        return ResultResponse.ok(response);
    }

    @PostMapping("/authenticate")
    @Schema(title = "인증", description = "재 로그인시 기존 토큰 제거")
    public ResultResponse<?> authenticateMember(
            @RequestBody
            AuthenticateMemberRequest request
    ) {
        var command = authenticationRequestMapStruct.toAuthenticateMemberCommand(request);
        authenticationInputPort.authenticateMember(command);
        return ResultResponse.ok("");
    }

    @PostMapping("/refresh-token")
    @Schema(title = "토큰 재발급")
    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        authenticationInputPort.refreshToken();
    }
}
