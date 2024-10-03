package com.pixelo.health.wellplate.myhealth.infrastructure.springrest.external.callee.diet;

import com.pixelo.health.wellplate.core.spi.AuthUser;
import com.pixelo.health.wellplate.core.spi.ResultResponse;
import com.pixelo.health.wellplate.myhealth.applidation.in.command.diet.CreateDietCommand;
import com.pixelo.health.wellplate.myhealth.applidation.in.command.diet.DietCommandInputPort;
import com.pixelo.health.wellplate.myhealth.applidation.vo.diet.DietVo;
import com.pixelo.health.wellplate.myhealth.infrastructure.springrest.external.callee.diet.request.CreateDietRequest;
import com.pixelo.health.wellplate.myhealth.infrastructure.springrest.external.callee.diet.response.CreatedDietResponse;
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
}
