package com.pixelo.health.wellplate.membership.application.service;

import com.pixelo.health.wellplate.membership.application.in.query.MemberQueryInputPort;
import com.pixelo.health.wellplate.membership.application.out.MemberOutputPort;
import com.pixelo.health.wellplate.membership.application.vo.MemberShipVo;
import com.pixelo.health.wellplate.membership.application.vo.MemberVo;
import com.pixelo.health.wellplate.membership.application.vo.UserDetailVo;
import com.pixelo.health.wellplate.membership.domain.provider.MemberProviderImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MemberQueryService implements MemberQueryInputPort {
    private final MemberOutputPort memberOutputPort;
    private final MemberMapStruct memberMapStruct;

    @Override
    public MemberShipVo findMemberByEmail(String email) {
        var savedMember = MemberProviderImpl.from(memberOutputPort.findMemberByEmail(email));
        var memberVo = memberMapStruct.toMemberVo(savedMember);
        var userDetailVo = createUserDetailVo(savedMember);
        return createMemberShipVo(memberVo, userDetailVo);
    }

    @Override
    public MemberShipVo findMemberById(UUID memberId) {
        var savedMember = MemberProviderImpl.from(memberOutputPort.findMemberById(memberId));
        var memberVo = memberMapStruct.toMemberVo(savedMember);
        var userDetailVo = createUserDetailVo(savedMember);
        return createMemberShipVo(memberVo, userDetailVo);
    }


    private static UserDetailVo createUserDetailVo(MemberProviderImpl savedMember) {
        return UserDetailVo.builder()
                .password(savedMember.getPassword())
                .username(savedMember.getUsername())
                .accountNonExpired(savedMember.getAccountNonExpired())
                .accountNonLocked(savedMember.getAccountNonLocked())
                .credentialsNonExpired(savedMember.getCredentialsNonExpired())
                .enabled(savedMember.getEnabled())
                .authorities(savedMember.getAuthorities())
                .build();
    }

    private static MemberShipVo createMemberShipVo(MemberVo memberVo, UserDetailVo userDetailVo) {
        return MemberShipVo.builder()
                .memberVo(memberVo)
                .userDetailVo(userDetailVo)
                .build();
    }


}
