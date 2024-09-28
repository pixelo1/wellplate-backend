package com.pixelo.health.wellplate.myhealth.applidation.vo.health;

import com.pixelo.health.wellplate.myhealth.domain.health.provider.HealthProvider;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface HealthVoMapStruct {

    HealthVo toHealthVo(HealthProvider provider);
}
