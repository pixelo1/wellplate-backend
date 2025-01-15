package com.pixelo.health.wellplate.myhealth.application.out;

import com.pixelo.health.wellplate.myhealth.domain.diet.Diet;

import java.util.List;
import java.util.UUID;

public interface DietOutputPort {

    Diet save(Diet diet);
    List<Diet> findByWellnessChallenger(UUID wellnessChallenger, UUID healthId);
}
