package com.pixelo.health.wellplate.framework.filter;

import com.pixelo.health.wellplate.framework.spi.AuthUser;
import com.pixelo.health.wellplate.framework.spi.*;
import com.pixelo.health.wellplate.framework.token.TokenExtractor;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

// bean으로 만들경우 servlet fileter에 자동으로 등록되어 security에서 걸러도 자동 식행됨
@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final TokenExtractor tokenGeneratorOutputPort;
    private final UserService userService;
    private final static String HEADER_AUTHORIZATION = "Authorization";
    private final static String TOKEN_PREFIX = "Bearer ";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.info("request : {}", request.toString());
        var authorizationHeader = request.getHeader(HEADER_AUTHORIZATION);
        log.info("authorizationHeader : {}", authorizationHeader);
        var jwtToken = getToken(authorizationHeader);
        log.info("jwtToken : {}", jwtToken);
        if (ObjectUtils.isEmpty(jwtToken)) {
            throw new IllegalArgumentException("Token is null");
        }

        var memberId = tokenGeneratorOutputPort.extractMemberId(jwtToken);
        var jwtUserDetails = userService.findUserById(memberId);

        AuthUser authUser = AuthUser.builder()
                .wellnessChallengerId(memberId)
                .build();
        var authenticationToken = new UsernamePasswordAuthenticationToken(authUser, jwtToken, jwtUserDetails.getAuthorities());
        var webAuthenticationDetails = new WebAuthenticationDetailsSource().buildDetails(request);
        authenticationToken.setDetails(webAuthenticationDetails);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        filterChain.doFilter(request,response);
    }

    private String getToken(String authorizationHeader){
        if(authorizationHeader != null && authorizationHeader.startsWith(TOKEN_PREFIX)){
            return authorizationHeader.substring(TOKEN_PREFIX.length());
        }
        return null;
    }
}
