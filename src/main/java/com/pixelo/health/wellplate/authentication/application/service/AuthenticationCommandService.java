package com.pixelo.health.wellplate.authentication.application.service;

import com.pixelo.health.wellplate.authentication.application.in.command.AuthenticateMemberCommand;
import com.pixelo.health.wellplate.authentication.application.in.command.AuthenticationInputPort;
import com.pixelo.health.wellplate.authentication.application.in.command.RegisterTokenAndMemberCommand;
import com.pixelo.health.wellplate.authentication.application.out.MemberShipOutputPort;
import com.pixelo.health.wellplate.authentication.application.out.RegisterMemberRequest;
import com.pixelo.health.wellplate.authentication.application.out.TokenOutputPort;
import com.pixelo.health.wellplate.authentication.application.vo.TokenVo;
import com.pixelo.health.wellplate.authentication.domain.token.Token;
import com.pixelo.health.wellplate.core.auth.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthenticationCommandService implements AuthenticationInputPort {

    private final MemberShipOutputPort memberShipOutputPort;
    private final TokenOutputPort tokenOutputPort;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final AuthenticationManager authenticationManager;

    @Override
    public TokenVo registerTokenAndMember(RegisterTokenAndMemberCommand command) {

        var registerMemberCommand = RegisterMemberRequest.builder()
                .email(command.email())
                .password(passwordEncoder.encode(command.password()))
                .build();
        var registeredUserDetailsResponse = memberShipOutputPort.registerMemberCommand(registerMemberCommand);

        var jwtToken = jwtProvider.generateToken(registeredUserDetailsResponse);
        var refreshToken = jwtProvider.generateRefreshToken(registeredUserDetailsResponse);
        saveUserToken(registeredUserDetailsResponse.memberId(), jwtToken);
        return TokenVo.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }

    private void saveUserToken(UUID memberId, String jwtToken) {
        var token = Token.builder()
                .memberId(memberId)
                .token(jwtToken)
                .build();
        tokenOutputPort.save(token);
    }

    @Override
    public void authenticateMember(AuthenticateMemberCommand command) {

    }

    @Override
    public void refreshToken() {

    }
}
