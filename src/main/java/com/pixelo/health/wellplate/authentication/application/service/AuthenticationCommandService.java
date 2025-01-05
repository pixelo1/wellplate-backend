package com.pixelo.health.wellplate.authentication.application.service;

import com.pixelo.health.wellplate.authentication.application.in.command.AuthenticateMemberCommand;
import com.pixelo.health.wellplate.authentication.application.in.AuthenticationCommandInputPort;
import com.pixelo.health.wellplate.authentication.application.in.command.RefreshTokenCommand;
import com.pixelo.health.wellplate.authentication.application.out.*;
import com.pixelo.health.wellplate.authentication.application.vo.TokenVo;
import com.pixelo.health.wellplate.authentication.domain.member.AuthMember;
import com.pixelo.health.wellplate.authentication.domain.token.Token;
import com.pixelo.health.wellplate.core.spi.JwtProvider;
import com.pixelo.health.wellplate.core.spi.JwtUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthenticationCommandService implements AuthenticationCommandInputPort {

    private final TokenOutputPort tokenOutputPort;
    private final JwtProvider jwtProvider;
    private final AuthenticationManager authenticationManager;
    private final AuthMemberOutputPort authMemberOutputPort;

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
        var authenticationToken = new UsernamePasswordAuthenticationToken(command.loginId(), command.password());
        authenticationManager.authenticate(authenticationToken); // springSecurity의 인증 처리로직 그대로 사용함

        //todo : 회원정보 조회 -> 인증 BC에 맞게 생성
        var member = authMemberOutputPort.findMemberByLoginId(command.loginId());
        var jwtUserDetails = toJwtUserDetails(member);
        var jwtToken = jwtProvider.generateToken(jwtUserDetails);
        var refreshToken = jwtProvider.generateRefreshToken(jwtUserDetails);

        revokeAllUserTokens(jwtUserDetails);
        saveUserToken(jwtUserDetails.memberId(), jwtToken);
        return TokenVo.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }

    private void revokeAllUserTokens(JwtUserDetails jwtUserDetails) {
        var validMemberTokens = tokenOutputPort.findAllValidTokenByMemberId(jwtUserDetails.memberId());
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
        var memberId = jwtProvider.extractMemberId(refreshToken);
        //todo : 회원정보 조회 -> 인증 BC에 맞게 생성
        var member = authMemberOutputPort.findMemberByMemberId(memberId);

        var jwtUserDetails = toJwtUserDetails(member);
        var accessToken = jwtProvider.generateToken(jwtUserDetails);
        revokeAllUserTokens(jwtUserDetails);
        saveUserToken(jwtUserDetails.memberId(), accessToken);
        return TokenVo.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    private JwtUserDetails toJwtUserDetails(AuthMember authMember) {
        return JwtUserDetails.builder()
                .memberId(authMember.memberId())
                .password(authMember.password())
                .username(authMember.getUsername())
                .accountNonExpired(authMember.isAccountNonExpired())
                .accountNonLocked(authMember.isAccountNonLocked())
                .credentialsNonExpired(authMember.isCredentialsNonExpired())
                .enabled(authMember.isEnabled())
                .authorities(authMember.getAuthorities())
                .build();
    }
}
