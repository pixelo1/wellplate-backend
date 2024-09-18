package com.pixelo.health.wellplate.core.filter;

import com.pixelo.health.wellplate.core.auth.JwtProvider;
import com.pixelo.health.wellplate.core.auth.TokenExpiredException;
import com.pixelo.health.wellplate.core.spi.AuthUser;
import com.pixelo.health.wellplate.core.spi.JwtUserDetails;
import com.pixelo.health.wellplate.core.spi.UserService;
import com.pixelo.health.wellplate.core.spi.TokenFacadeInCore;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtProvider jwtProvider;
    private final UserService userService;
    private final TokenFacadeInCore tokenFacadeInCore;
    private final static String HEADER_AUTHORIZATION = "Authorization";
    private final static String TOKEN_PREFIX = "Bearer ";
    private final static String EXCLUDE_PATH = "/api/v1/auth";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // path /api/v1/auth 에서 필터 실행되는지 확인 필요 - 들어오면 제외 -
        if (request.getServletPath().contains(EXCLUDE_PATH)) {
            filterChain.doFilter(request, response);
            return;
        }
        var authorizationHeader = request.getHeader(HEADER_AUTHORIZATION);
        var jwtToken = getToken(authorizationHeader);
        if (jwtToken == null) {
            filterChain.doFilter(request,response);
            return;
        }
        if (authenticationTokenValidation()) {
            filterChain.doFilter(request,response);
            return;
        }
        tokenValidation(jwtToken);

        var memberId = jwtProvider.extractMemberId(jwtToken);
        var jwtUserDetails = userService.findUserById(memberId); // todo 토큰에 권한까지 넣어서 제거하자

        //UsernamePasswordAuthenticationToken 생성되면서 isAuthenticated true로 들어간다
        //authUser
        AuthUser authUser = AuthUser.builder()
                .memberId(memberId)
                .build();
        var authenticationToken = new UsernamePasswordAuthenticationToken(authUser, jwtToken, jwtUserDetails.getAuthorities());
        var webAuthenticationDetails = new WebAuthenticationDetailsSource().buildDetails(request);
        authenticationToken.setDetails(webAuthenticationDetails);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        filterChain.doFilter(request,response);
    }

    private static boolean authenticationTokenValidation() {
        return SecurityContextHolder.getContext().getAuthentication() != null && SecurityContextHolder.getContext().getAuthentication().isAuthenticated();
    }

    private void tokenValidation(String jwtToken) {
        if (jwtProvider.isTokenExpired(jwtToken)) {
            throw new TokenExpiredException();
        }
        var validatedToken = tokenFacadeInCore.validateToken(jwtToken);
        if (!validatedToken) {
            throw new TokenExpiredException();
        }
    }

    private String getToken(String authorizationHeader){
        if(authorizationHeader != null && authorizationHeader.startsWith(TOKEN_PREFIX)){
            return authorizationHeader.substring(TOKEN_PREFIX.length());
        }
        return null;
    }
}
