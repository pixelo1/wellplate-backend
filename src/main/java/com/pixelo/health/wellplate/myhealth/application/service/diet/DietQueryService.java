package com.pixelo.health.wellplate.myhealth.application.service.diet;

import com.pixelo.health.wellplate.myhealth.application.in.query.diet.DietQueryInputPort;
import com.pixelo.health.wellplate.myhealth.application.in.query.diet.GetRegisteredDietQuery;
import com.pixelo.health.wellplate.myhealth.application.out.DietOutputPort;
import com.pixelo.health.wellplate.myhealth.application.vo.diet.DietVo;
import com.pixelo.health.wellplate.myhealth.application.vo.diet.DietVoMapStruct;
import com.pixelo.health.wellplate.myhealth.domain.diet.Diet;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DietQueryService implements DietQueryInputPort {

    private final DietOutputPort dietOutputPort;
    private final DietVoMapStruct dietVoMapStruct;


    @Override
    public List<DietVo> getRegisteredDiet(GetRegisteredDietQuery query) {
        List<Diet> diets = dietOutputPort.findByWellnessChallenger(query.wellnessChallengerId(), query.healthId());
        return diets.stream()
                .map(dietVoMapStruct::toDietVo)
                .toList();
    }
}
