package com.pixelo.health.wellplate.myhealth.applidation.service.diet;

import com.pixelo.health.wellplate.myhealth.applidation.in.command.diet.CreateDietCommand;
import com.pixelo.health.wellplate.myhealth.applidation.in.command.diet.DietCommandInputPort;
import com.pixelo.health.wellplate.myhealth.applidation.out.DietOutputPort;
import com.pixelo.health.wellplate.myhealth.applidation.vo.diet.DietVo;
import com.pixelo.health.wellplate.myhealth.applidation.vo.diet.DietVoMapStruct;
import com.pixelo.health.wellplate.myhealth.domain.diet.Diet;
import com.pixelo.health.wellplate.myhealth.domain.diet.adapter.DietAdapter;
import com.pixelo.health.wellplate.myhealth.domain.diet.domainservice.DietFactory;
import com.pixelo.health.wellplate.myhealth.domain.diet.domainservice.dto.CreateDietDto;
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
    @Override
    public DietVo createDiet(CreateDietCommand command) {
        var createDietDto = dietCommandMapStruct.toCreateDietDto(command);
        var diet = DietFactory.createDiet(createDietDto);
        var savedDiet = dietOutputPort.save(diet);
        var dietAdapter = DietAdapter.builder().diet(savedDiet).build();
        return dietVoMapStruct.toDietVo(dietAdapter);
    }
}
