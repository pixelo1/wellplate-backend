package com.pixelo.health.wellplate.membership.application.service;

import com.pixelo.health.wellplate.membership.application.in.command.MemberInputPort;
import com.pixelo.health.wellplate.membership.application.in.command.RegisterMemberCommand;
import com.pixelo.health.wellplate.membership.application.vo.MemberShipVo;
import com.pixelo.health.wellplate.membership.application.vo.MemberVo;
import com.pixelo.health.wellplate.membership.application.vo.UserDetailVo;
import com.pixelo.health.wellplate.membership.domain.provider.MemberProviderImpl;
import com.pixelo.health.wellplate.membership.application.out.MemberOutputPort;
import com.pixelo.health.wellplate.membership.domain.domainservices.MemberFactory;
import com.pixelo.health.wellplate.membership.domain.domainservices.dtos.CreateMemberDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberCommandService implements MemberInputPort {
    private final MemberOutputPort memberOutputPort;
    private final MemberMapStruct memberMapStruct;
    @Override
    public MemberShipVo registerMemberCommand(RegisterMemberCommand command) {
        var dto = CreateMemberDto.builder()
                .email(command.email())
                .password(command.password())
                .build();

        var member = MemberFactory.createMemberTypeOfWellnessChallenger(dto);
        var savedMember = MemberProviderImpl.from(memberOutputPort.save(member));
        var memberVo = memberMapStruct.toMemberVo(savedMember);
        var userDetailVo = createUserDetailVo(savedMember);
        return createMemberShipVo(memberVo, userDetailVo);
    }

    private static MemberShipVo createMemberShipVo(MemberVo memberVo, UserDetailVo userDetailVo) {
        return MemberShipVo.builder()
                .memberVo(memberVo)
                .userDetailVo(userDetailVo)
                .build();
    }

    @Override
    public MemberShipVo findMemberByEmail(String email) {
        var savedMember = MemberProviderImpl.from(memberOutputPort.findMemberByEmail(email));
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

}
