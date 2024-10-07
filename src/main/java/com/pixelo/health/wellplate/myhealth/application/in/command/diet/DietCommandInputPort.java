package com.pixelo.health.wellplate.myhealth.application.in.command.diet;

import com.pixelo.health.wellplate.myhealth.application.vo.diet.DietVo;

public interface DietCommandInputPort {

    DietVo createDiet(CreateDietCommand command);
}
