package com.pixelo.health.wellplate.authentication.infrastructure.springrest.external.callee;

import com.pixelo.health.wellplate.authentication.application.in.command.AuthenticationCommandInputPort;
import com.pixelo.health.wellplate.authentication.application.in.command.RefreshTokenCommand;
import com.pixelo.health.wellplate.authentication.application.in.command.TokenCommandInputPort;
import com.pixelo.health.wellplate.authentication.application.vo.TokenVo;
import com.pixelo.health.wellplate.authentication.infrastructure.springrest.external.callee.request.AuthenticateMemberRequest;
import com.pixelo.health.wellplate.authentication.infrastructure.springrest.external.callee.request.LogoutRequest;
import com.pixelo.health.wellplate.authentication.infrastructure.springrest.external.callee.request.RefreshTokenRequest;
import com.pixelo.health.wellplate.authentication.infrastructure.springrest.external.callee.request.RegisterTokenAndMemberRequest;
import com.pixelo.health.wellplate.authentication.infrastructure.springrest.external.callee.response.AuthenticationResponse;
import com.pixelo.health.wellplate.core.spi.ResultResponse;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationCalleeExternalRestAdapter {

    private final AuthenticationCommandInputPort authenticationCommandInputPort;
    private final AuthenticationRequestMapStruct authenticationRequestMapStruct;
    private final AuthenticationResponseMapStruct authenticationResponseMapStruct;
    private final TokenCommandInputPort tokenCommandInputPort;

    @PostMapping("/register")
    @Operation(summary = "회원가입 요청, 토큰 발급")
    public ResultResponse<AuthenticationResponse> registerTokenAndMember(
            @RequestBody
            RegisterTokenAndMemberRequest request
    ) {
        var command = authenticationRequestMapStruct.toRegisterTokenAndMemberCommand(request);
        var tokenVo = authenticationCommandInputPort.registerTokenAndMember(command);
        var response = authenticationResponseMapStruct.toAuthenticationResponse(tokenVo);
        return ResultResponse.ok(response);
    }

    @PostMapping("/authenticate")
    @Operation(summary = "인증 및 로그인", description = "재 로그인시 기존 토큰 제거")
    public ResultResponse<AuthenticationResponse> authenticateMember(
            @RequestBody
            AuthenticateMemberRequest request
    ) {
        var command = authenticationRequestMapStruct.toAuthenticateMemberCommand(request);
        var tokenVo = authenticationCommandInputPort.authenticateMember(command);
        var response = authenticationResponseMapStruct.toAuthenticationResponse(tokenVo);
        return ResultResponse.ok(response);
    }

    @PostMapping("/refresh-token")
    @Operation(summary = "토큰 재발급")
    public ResultResponse<AuthenticationResponse> refreshToken(
            @RequestBody
            RefreshTokenRequest request
    ) {
        var command = authenticationRequestMapStruct.toRefreshTokenCommand(request);
        var tokenVo = authenticationCommandInputPort.refreshToken(command);
        var response = authenticationResponseMapStruct.toAuthenticationResponse(tokenVo);
        return ResultResponse.ok(response);
    }

    @Operation(summary = "로그아웃", description = "사용자를 로그아웃합니다.")
    @PostMapping("/logout")
    public ResultResponse<?> logout(
            @RequestBody
            LogoutRequest request
    ) {
        tokenCommandInputPort.updateTokenNotUsable(request.getAccessToken());
        SecurityContextHolder.clearContext();

        return ResultResponse.ok("로그아웃 성공");
    }
}
