package com.pixelo.health.wellplate.health.infrastructure.springrest.external.callee.health;

import com.pixelo.health.wellplate.core.spi.AuthUser;
import com.pixelo.health.wellplate.core.spi.ResultResponse;
import com.pixelo.health.wellplate.health.infrastructure.springrest.external.callee.health.request.RegisterHealthRequest;
import com.pixelo.health.wellplate.health.infrastructure.springrest.external.callee.health.response.RegisteredHealthResponse;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/v1")
@RequiredArgsConstructor
public class HealthCalleeExternalRestAdapter {


    @PostMapping
    @Operation(summary = "건강 상태 등록", description = "현재 체중, 목표 체중 등록")
    public ResultResponse<RegisteredHealthResponse> registerHealth(
            @AuthenticationPrincipal
            AuthUser authUser,
            @RequestBody
            @Valid
            RegisterHealthRequest request
    ) {

        var response = RegisteredHealthResponse.builder().build();
        return ResultResponse.ok(response);
    }

}
