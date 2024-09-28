package com.pixelo.health.wellplate.myhealth.infrastructure.springrest.external.callee.diet;

import com.pixelo.health.wellplate.core.spi.AuthUser;
import com.pixelo.health.wellplate.core.spi.ResultResponse;
import com.pixelo.health.wellplate.myhealth.applidation.in.command.diet.DietCommandInputPort;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "내 식단 API")
@RequestMapping("/api/v1/diet")
public class DietCalleeExternalRestAdapter {

    private final DietCommandInputPort dietCommandInputPort;


    @PostMapping
    @Operation(summary = "특정 시간의 식사 등록")
    public ResultResponse<?> createDiet(
            @AuthenticationPrincipal AuthUser authUser
//            @RequestBody @Valid CreateDiet
            ) {
        dietCommandInputPort.test();
        return ResultResponse.ok("");
    }
}
