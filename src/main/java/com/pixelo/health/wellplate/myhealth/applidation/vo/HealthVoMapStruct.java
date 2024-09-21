package com.pixelo.health.wellplate.myhealth.applidation.vo;

import com.pixelo.health.wellplate.myhealth.applidation.in.command.RegisterHealthCommand;
import com.pixelo.health.wellplate.myhealth.applidation.vo.HealthVo;
import com.pixelo.health.wellplate.myhealth.domain.health.domainservice.dtos.CreateHealthDto;
import com.pixelo.health.wellplate.myhealth.domain.health.provider.HealthProvider;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface HealthVoMapStruct {

    HealthVo toHealthVo(HealthProvider provider);
}
