package com.pixelo.health.wellplate.myhealth.infrastructure.springrest.external.callee.diet;

import com.pixelo.health.wellplate.framework.spi.AuthUser;
import com.pixelo.health.wellplate.framework.spi.ResultResponse;
import com.pixelo.health.wellplate.myhealth.application.in.command.diet.DietCommandInputPort;
import com.pixelo.health.wellplate.myhealth.application.in.query.diet.DietQueryInputPort;
import com.pixelo.health.wellplate.myhealth.application.in.query.diet.GetRegisteredDietQuery;
import com.pixelo.health.wellplate.myhealth.infrastructure.springrest.external.callee.diet.request.CreateDietRequest;
import com.pixelo.health.wellplate.myhealth.infrastructure.springrest.external.callee.diet.response.CreatedDietResponse;
import com.pixelo.health.wellplate.myhealth.infrastructure.springrest.external.callee.diet.response.GetRegisteredDietResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@Tag(name = "내 식단 API")
@RequestMapping("/api/v1/health/{healthId}/diets")
public class DietCalleeExternalRestAdapter {

    private final DietCommandInputPort dietCommandInputPort;
    private final DietRequestMapStruct dietRequestMapStruct;
    private final DietResponseMapStruct dietResponseMapStruct;
    private final DietQueryInputPort dietQueryInputPort;


    @PostMapping("")
    @Operation(summary = "특정 시간의 식사 등록")
    public ResultResponse<CreatedDietResponse> createDiet(
            @AuthenticationPrincipal AuthUser authUser,
            @PathVariable("healthId") UUID healthId,
            @RequestBody @Valid CreateDietRequest createDietRequest) {
        var command = dietRequestMapStruct.toCreateDietCommand(createDietRequest, authUser, healthId);
        var dietVo = dietCommandInputPort.createDiet(command);
        var response = dietResponseMapStruct.toCreatedDietResponse(dietVo);
        return ResultResponse.ok(response);
    }

    @GetMapping
    @Operation(summary = "내가 등록한 식단 조회")
    public ResultResponse<GetRegisteredDietResponse> getDiet(
            @AuthenticationPrincipal AuthUser authUser,
            @PathVariable("healthId") UUID healthId) {
        var query = GetRegisteredDietQuery.builder()
                .wellnessChallengerId(authUser.wellnessChallengerId())
                .healthId(healthId)
                .build();
        var dietVos = dietQueryInputPort.getRegisteredDiet(query);
        var dietResponse = dietVos.stream().map(dietResponseMapStruct::toGetRegisteredDietResponseDietResponse)
                .toList();
        var response = GetRegisteredDietResponse.builder()
                .diets(dietResponse)
                .build();
        return ResultResponse.ok(response);
    }
}
