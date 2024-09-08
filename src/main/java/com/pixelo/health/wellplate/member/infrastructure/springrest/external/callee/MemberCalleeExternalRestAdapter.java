package com.pixelo.health.wellplate.member.infrastructure.springrest.external.callee;

import com.pixelo.health.wellplate.core.spi.ResultResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
@Tag(name = "회원 관리 - 가입, 상세")
public class MemberCalleeExternalRestAdapter {

    @PostMapping
    @Operation(summary = "회원가입")
    public ResultResponse<?> test() {
        return ResultResponse.ok("ok");
    }
}
