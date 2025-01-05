package com.pixelo.health.wellplate.membership.application.service;

import com.pixelo.health.wellplate.membership.application.vo.MemberVo;
import com.pixelo.health.wellplate.membership.domain.provider.MemberEntityProvider;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MemberMapStruct {

    MemberVo toMemberVo(MemberEntityProvider memberEntityProvider);
}
