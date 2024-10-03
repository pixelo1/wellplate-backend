package com.pixelo.health.wellplate.myhealth.infrastructure.springrest.external.callee.diet;

import com.pixelo.health.wellplate.core.spi.AuthUser;
import com.pixelo.health.wellplate.core.spi.ResultResponse;
import com.pixelo.health.wellplate.myhealth.applidation.in.command.diet.CreateDietCommand;
import com.pixelo.health.wellplate.myhealth.applidation.in.command.diet.DietCommandInputPort;
import com.pixelo.health.wellplate.myhealth.applidation.vo.diet.DietVo;
import com.pixelo.health.wellplate.myhealth.applidation.vo.diet.FoodVo;
import com.pixelo.health.wellplate.myhealth.applidation.vo.health.HealthVo;
import com.pixelo.health.wellplate.myhealth.infrastructure.springrest.external.callee.diet.request.CreateDietRequest;
import com.pixelo.health.wellplate.myhealth.infrastructure.springrest.external.callee.diet.response.CreatedDietResponse;
import com.pixelo.health.wellplate.myhealth.infrastructure.springrest.external.callee.health.HealthRequestMapStructImpl;
import com.pixelo.health.wellplate.myhealth.infrastructure.springrest.external.callee.health.HealthResponseMapStructImpl;
import com.pixelo.health.wellplate.myhealth.infrastructure.springrest.external.callee.health.request.RegisterHealthRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class DietCalleeExternalRestAdapterTest {

    @Mock
    DietCommandInputPort dietCommandInputPort;
    @Mock
    DietRequestMapStruct dietRequestMapStruct;
    @Mock
    DietResponseMapStruct dietResponseMapStruct;
    @InjectMocks
    DietCalleeExternalRestAdapter dietCalleeExternalRestAdapter;

    private static DietRequestMapStructImpl dietRequestMapStructImpl;
    private static DietResponseMapStructImpl dietResponseMapStructImpl;
    private static Validator validator;


    @BeforeAll
    static void setup() {
        dietRequestMapStructImpl = new DietRequestMapStructImpl();
        dietResponseMapStructImpl = new DietResponseMapStructImpl();
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    void 먹은식단을_등록한다_필수값() {
        //given
        var authUser = AuthUser.builder()
                .wellnessChallengerId(UUID.randomUUID())
                .build();
        var healthId = UUID.randomUUID();

        var foodInfoRequest = CreateDietRequest.FoodInfoRequest.builder()
                .foodName("된장찌개")
                .calorie(BigDecimal.valueOf(300.2))
                .build();
        var dietRequest = CreateDietRequest.builder()
//                .healthId(UUID.randomUUID())
                .mealTime(LocalDate.now())
                .foodInfos(List.of(foodInfoRequest))
                .build();

        var foodVo = FoodVo.builder()
                .name(foodInfoRequest.getFoodName())
                .calorie(foodInfoRequest.getCalorie())
                .build();

        var dietVo = DietVo.builder()
                .dietId(UUID.randomUUID())
                .healthId(healthId)
                .wellnessChallengerId(authUser.wellnessChallengerId())
                .mealTime(dietRequest.getMealTime())
                .foodVos(List.of(foodVo))
                .build();

        Mockito.when(dietCommandInputPort.createDiet(Mockito.any(CreateDietCommand.class)))
                .thenReturn(dietVo);

        var command = dietRequestMapStructImpl.toCreateDietCommand(dietRequest, authUser, healthId);
        Mockito.when(dietRequestMapStruct.toCreateDietCommand(Mockito.any(CreateDietRequest.class), Mockito.any(AuthUser.class), Mockito.any(UUID.class)))
                .thenReturn(command);

        var response = dietResponseMapStructImpl.toCreatedDietResponse(dietVo);
        Mockito.when(dietResponseMapStruct.toCreatedDietResponse(Mockito.any(DietVo.class)))
                .thenReturn(response);

        //when
        ResultResponse<CreatedDietResponse> result = dietCalleeExternalRestAdapter.createDiet(authUser, healthId, dietRequest);

        //then
        Assertions.assertThat(result.data()).isNotNull();
        Assertions.assertThat(result.data().getDietId()).isEqualTo(dietVo.dietId());
        Mockito.verify(dietCommandInputPort, Mockito.times(1)).createDiet(Mockito.any(CreateDietCommand.class));
        Mockito.verify(dietRequestMapStruct, Mockito.times(1)).toCreateDietCommand(Mockito.any(CreateDietRequest.class), Mockito.any(AuthUser.class), Mockito.any(UUID.class));
        Mockito.verify(dietResponseMapStruct, Mockito.times(1)).toCreatedDietResponse(Mockito.any(DietVo.class));
    }

    @Test
    void 식사_음식_등록_요청_필수값_에러() {
        //given
        var dietRequest = CreateDietRequest.builder()
                .build();
        var foodInfoRequest = CreateDietRequest.FoodInfoRequest.builder()
                .build();
        var foodInfoRequestNegativeCalorie = CreateDietRequest.FoodInfoRequest.builder()
                .calorie(BigDecimal.valueOf(-1))
                .build();
        //when
        var validateDietRequestResult = validator.validate(dietRequest);
        var validateFoodInfoRequestResult = validator.validate(foodInfoRequest);
        var validateFoodRequestNegativeCalorie = validator.validate(foodInfoRequestNegativeCalorie);
        //then
        Assertions.assertThat(validateDietRequestResult.size()).isEqualTo(2);
        Assertions.assertThat(validateFoodInfoRequestResult.size()).isEqualTo(2);
        Assertions.assertThat(validateFoodRequestNegativeCalorie.size()).isEqualTo(2);
    }
}