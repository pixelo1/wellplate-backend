package com.pixelo.health.wellplate.membership.infrastructure.springrest.internal;

import com.pixelo.health.wellplate.membership.MemberShipFacadeVo;
import com.pixelo.health.wellplate.membership.application.vo.MemberShipVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MemberFacadeMapStruct {
    MemberShipFacadeVo toMemberShipFacadeVo(MemberShipVo vo);
}
