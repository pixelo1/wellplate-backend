package com.pixelo.health.wellplate.myhealth.infrastructure.springrest.external.callee.health;

import com.pixelo.health.wellplate.framework.spi.AuthUser;
import com.pixelo.health.wellplate.framework.spi.ResultResponse;
import com.pixelo.health.wellplate.myhealth.application.in.command.health.HealthCommandInputPort;
import com.pixelo.health.wellplate.myhealth.application.in.query.health.GetRegisteredHealthQuery;
import com.pixelo.health.wellplate.myhealth.application.in.query.health.HealthQueryInputPort;
import com.pixelo.health.wellplate.myhealth.infrastructure.springrest.external.callee.health.request.RegisterHealthRequest;
import com.pixelo.health.wellplate.myhealth.infrastructure.springrest.external.callee.health.response.FindRegisteredHealthResponse;
import com.pixelo.health.wellplate.myhealth.infrastructure.springrest.external.callee.health.response.RegisteredHealthResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Tag(name = "내 건강 정보 API")
@RequestMapping("/api/v1/health")
public class HealthCalleeExternalRestAdapter {

    private final HealthRequestMapStruct healthRequestMapStruct;
    private final HealthResponseMapStruct healthResponseMapStruct;
    private final HealthCommandInputPort healthCommandInputPort;
    private final HealthQueryInputPort healthQueryInputPort;

    @PostMapping
    @Operation(summary = "건강 상태 등록", description = "현재 체중, 목표 체중 등록")
    public ResultResponse<RegisteredHealthResponse> registerHealth(@AuthenticationPrincipal AuthUser authUser,
                                                                   @RequestBody @Valid RegisterHealthRequest request) {
        var command = healthRequestMapStruct.toRegisterHealthCommand(request, authUser);
        var healthVo = healthCommandInputPort.registerHealthCommand(command);
        var response = healthResponseMapStruct.toRegisteredHealthResponse(healthVo);
        return ResultResponse.ok(response);
    }

    @GetMapping
    @Operation(summary = "건강 상태 조회", description = "현재 체중, 목표 체중 조회")
    public ResultResponse<FindRegisteredHealthResponse> getHealth(@AuthenticationPrincipal AuthUser authUser) {
        var query = GetRegisteredHealthQuery.builder()
                .wellnessChallengerId(authUser.wellnessChallengerId())
                .build();
        var healthVo = healthQueryInputPort.getRegisteredHealth(query);
        var response = healthResponseMapStruct.toFindRegisteredHealthResponse(healthVo);
        return ResultResponse.ok(response);
    }
}
