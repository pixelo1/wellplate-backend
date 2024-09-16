package com.pixelo.health.wellplate.authentication.infrastructure.springrest.external.callee.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
@AllArgsConstructor
@Schema(title = "인증 요청 - 로그인")
public class AuthenticateMemberRequest {
    @Email
    @NotNull(message = "이메일은 필수입니다")
    private String email;
    @NotNull(message = "비밀번호는 필수 입니다.")
    private String password;
}
