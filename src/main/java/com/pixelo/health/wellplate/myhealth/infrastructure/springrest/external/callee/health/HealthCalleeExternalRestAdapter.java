package com.pixelo.health.wellplate.myhealth.infrastructure.springrest.external.callee.health;

import com.pixelo.health.wellplate.core.spi.AuthUser;
import com.pixelo.health.wellplate.core.spi.ResultResponse;
import com.pixelo.health.wellplate.myhealth.applidation.in.command.HealthCommandInputPort;
import com.pixelo.health.wellplate.myhealth.applidation.in.command.RegisterHealthCommand;
import com.pixelo.health.wellplate.myhealth.applidation.vo.HealthVo;
import com.pixelo.health.wellplate.myhealth.infrastructure.springrest.external.callee.HealthRequestMapStruct;
import com.pixelo.health.wellplate.myhealth.infrastructure.springrest.external.callee.health.request.RegisterHealthRequest;
import com.pixelo.health.wellplate.myhealth.infrastructure.springrest.external.callee.health.response.RegisteredHealthResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/v1/health")
@RequiredArgsConstructor
@Tag(name = "v1 - 내 건강 정보 API")
public class HealthCalleeExternalRestAdapter {

    private final HealthCommandInputPort healthCommandInputPort;
    private final HealthRequestMapStruct healthRequestMapStruct;

    @PostMapping
    @Operation(summary = "건강 상태 등록", description = "현재 체중, 목표 체중 등록")
    public ResultResponse<RegisteredHealthResponse> registerHealth(@AuthenticationPrincipal AuthUser authUser,
                                                                   @RequestBody @Valid RegisterHealthRequest request) {
        var command = healthRequestMapStruct.toRegisterHealthCommand(request, authUser);
        var healthVo = healthCommandInputPort.registerHealthCommand(command);
        var response = RegisteredHealthResponse.of(healthVo);
        return ResultResponse.ok(response);
    }

}
