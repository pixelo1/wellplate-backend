package com.pixelo.health.wellplate.myhealth.application.out;

import com.pixelo.health.wellplate.myhealth.domain.diet.Diet;

public interface DietOutputPort {

    Diet save(Diet diet);
}
