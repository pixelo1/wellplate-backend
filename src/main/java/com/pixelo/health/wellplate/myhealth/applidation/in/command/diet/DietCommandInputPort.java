package com.pixelo.health.wellplate.myhealth.applidation.in.command.diet;

import com.pixelo.health.wellplate.myhealth.applidation.vo.diet.DietVo;

public interface DietCommandInputPort {

    DietVo createDiet(CreateDietCommand command);
}
