package com.pixelo.health.wellplate.authentication.application.service;

import com.pixelo.health.wellplate.authentication.application.in.command.AuthenticateMemberCommand;
import com.pixelo.health.wellplate.authentication.application.in.command.AuthenticationCommandInputPort;
import com.pixelo.health.wellplate.authentication.application.in.command.RefreshTokenCommand;
import com.pixelo.health.wellplate.authentication.application.in.command.RegisterTokenAndMemberCommand;
import com.pixelo.health.wellplate.authentication.application.out.MemberShipOutputPort;
import com.pixelo.health.wellplate.authentication.application.out.RegisterMemberRequest;
import com.pixelo.health.wellplate.authentication.application.out.RegisteredUserDetailsResponse;
import com.pixelo.health.wellplate.authentication.application.out.TokenOutputPort;
import com.pixelo.health.wellplate.authentication.application.vo.TokenVo;
import com.pixelo.health.wellplate.authentication.domain.token.Token;
import com.pixelo.health.wellplate.core.auth.JwtProvider;
import com.pixelo.health.wellplate.core.auth.TokenExpiredException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthenticationCommandService implements AuthenticationCommandInputPort {

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
    public TokenVo authenticateMember(AuthenticateMemberCommand command) {
        //security에서 처리 후 들어온다
        var authenticationToken = new UsernamePasswordAuthenticationToken(command.email(), command.password());
        authenticationManager.authenticate(authenticationToken); // springSecurity의 인증 처리로직 그대로 사용함
        // security context 에서 가져올수 있지 않을까?

        var userDetailsResponse = memberShipOutputPort.findMemberByEmail(command.email());
        var jwtToken = jwtProvider.generateToken(userDetailsResponse);
        var refreshToken = jwtProvider.generateRefreshToken(userDetailsResponse);

        revokeAllUserTokens(userDetailsResponse);
        saveUserToken(userDetailsResponse.memberId(), jwtToken);
        return TokenVo.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }

    private void revokeAllUserTokens(RegisteredUserDetailsResponse userDetails) {
        var validMemberTokens = tokenOutputPort.findAllValidTokenByMemberId(userDetails.memberId());
        if (validMemberTokens.isEmpty())
            return;
        validMemberTokens.forEach(Token::updateTokenRevoke);
        tokenOutputPort.saveAll(validMemberTokens);
    }

    @Override
    public TokenVo refreshToken(RefreshTokenCommand command) {
        var refreshToken = command.refreshToken();
        if (jwtProvider.isTokenExpired(refreshToken)) {
            throw new IllegalArgumentException("토큰이 만료되었습니다.");
        }
        var userEmail = jwtProvider.extractUsername(refreshToken);
        var userDetailsResponse = memberShipOutputPort.findMemberByEmail(userEmail);
        var accessToken = jwtProvider.generateToken(userDetailsResponse);
        revokeAllUserTokens(userDetailsResponse);
        saveUserToken(userDetailsResponse.memberId(), accessToken);
        return TokenVo.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }
}
