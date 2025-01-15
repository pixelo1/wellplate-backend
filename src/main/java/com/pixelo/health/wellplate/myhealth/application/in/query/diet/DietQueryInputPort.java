package com.pixelo.health.wellplate.myhealth.application.in.query.diet;

import com.pixelo.health.wellplate.myhealth.application.vo.diet.DietVo;

import java.util.List;

public interface DietQueryInputPort {


    List<DietVo> getRegisteredDiet(GetRegisteredDietQuery query);
}
