package com.pixelo.health.wellplate.myhealth.application.service.diet;

import com.pixelo.health.wellplate.myhealth.application.in.command.diet.CreateDietCommand;
import com.pixelo.health.wellplate.myhealth.application.out.DietOutputPort;
import com.pixelo.health.wellplate.myhealth.application.out.HealthOutputPort;
import com.pixelo.health.wellplate.myhealth.application.vo.diet.DietVo;
import com.pixelo.health.wellplate.myhealth.application.vo.diet.DietVoMapStruct;
import com.pixelo.health.wellplate.myhealth.application.vo.diet.DietVoMapStructImpl;
import com.pixelo.health.wellplate.myhealth.domain.diet.Diet;
import com.pixelo.health.wellplate.myhealth.domain.diet.adapter.DietAdapter;
import com.pixelo.health.wellplate.myhealth.domain.diet.domainservice.DietFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class DietCommandServiceTest {

    @Mock
    DietOutputPort dietOutputPort;
    @Mock
    DietCommandMapStruct dietCommandMapStruct;
    @Mock
    DietVoMapStruct dietVoMapStruct;
    @Mock
    HealthOutputPort healthOutputPort;
    @Mock
    DietFactory dietFactory;
    @InjectMocks
    DietCommandService dietCommandService;

    static DietCommandMapStructImpl dietCommandMapStructImpl;
    static DietVoMapStructImpl dietVoMapStructImpl;
    static DietFactory dietFactoryNew;
    @BeforeAll
    static void setup() {
        dietCommandMapStructImpl = new DietCommandMapStructImpl();
        dietVoMapStructImpl = new DietVoMapStructImpl();
        dietFactoryNew = new DietFactory();
    }

    @Test
    void 식단_등록_생성_필수값() {
        var createFoodInfo = CreateDietCommand.CreateFoodInfo.builder()
                .foodName("밥")
                .calorie(BigDecimal.valueOf(100))
                .build();
        var command = CreateDietCommand.builder()
                .wellnessChallengerId(UUID.randomUUID())
                .healthId(UUID.randomUUID())
                .mealTime(LocalDateTime.now())
                .foodInfos(List.of(createFoodInfo))
                .build();

        var createDietDto = dietCommandMapStructImpl.toCreateDietDto(command);
        Mockito.when(dietCommandMapStruct.toCreateDietDto(Mockito.any(CreateDietCommand.class)))
                .thenReturn(createDietDto);

        var diet = dietFactoryNew.createDiet(createDietDto);
        Mockito.when(dietFactory.createDiet(Mockito.any()))
                .thenReturn(diet);

        Mockito.when(dietOutputPort.save(Mockito.any(Diet.class)))
                .thenReturn(diet);

        var dietAdapter = DietAdapter.builder().diet(diet).build();
        var dietVo = dietVoMapStructImpl.toDietVo(dietAdapter);
        Mockito.when(dietVoMapStruct.toDietVo(Mockito.any(DietAdapter.class)))
                .thenReturn(dietVo);
        //when
        DietVo result = dietCommandService.createDiet(command);

        //then
        assertNotNull(result);
        assertEquals(diet.dietId(), result.dietId());
        assertEquals(diet.healthId(), result.healthId());
        assertEquals(diet.wellnessChallengerId(), result.wellnessChallengerId());
        assertEquals(diet.mealTime(), result.mealTime());
        assertEquals(diet.foodInfo().foods().get(0).name(), result.foodVos().get(0).name());
        assertEquals(diet.foodInfo().foods().get(0).calorie(), result.foodVos().get(0).calorie());

        Mockito.verify(healthOutputPort, Mockito.times(1)).checkHealthIdOrException(Mockito.any(UUID.class));
    }

}