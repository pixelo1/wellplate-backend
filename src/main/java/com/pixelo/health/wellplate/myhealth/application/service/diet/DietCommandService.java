package com.pixelo.health.wellplate.myhealth.application.service.diet;

import com.pixelo.health.wellplate.myhealth.application.in.command.diet.CreateDietCommand;
import com.pixelo.health.wellplate.myhealth.application.in.command.diet.DietCommandInputPort;
import com.pixelo.health.wellplate.myhealth.application.out.DietOutputPort;
import com.pixelo.health.wellplate.myhealth.application.out.HealthOutputPort;
import com.pixelo.health.wellplate.myhealth.application.vo.diet.DietVo;
import com.pixelo.health.wellplate.myhealth.application.vo.diet.DietVoMapStruct;
import com.pixelo.health.wellplate.myhealth.domain.diet.adapter.DietAdapter;
import com.pixelo.health.wellplate.myhealth.domain.diet.domainservice.DietFactory;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class DietCommandService implements DietCommandInputPort {

    private final DietOutputPort dietOutputPort;
    private final DietCommandMapStruct dietCommandMapStruct;
    private final DietVoMapStruct dietVoMapStruct;
    private final HealthOutputPort healthOutputPort;
    private final DietFactory dietFactory;

    @Override
    public DietVo createDiet(CreateDietCommand command) {
        healthOutputPort.checkHealthIdOrException(command.healthId());
        var createDietDto = dietCommandMapStruct.toCreateDietDto(command);
        var diet = dietFactory.createDiet(createDietDto);
        var savedDiet = dietOutputPort.save(diet);
        var dietAdapter = DietAdapter.builder().diet(savedDiet).build();
        return dietVoMapStruct.toDietVo(dietAdapter);
    }
}
