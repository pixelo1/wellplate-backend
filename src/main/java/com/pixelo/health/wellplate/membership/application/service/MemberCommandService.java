package com.pixelo.health.wellplate.membership.application.service;

import com.pixelo.health.wellplate.membership.application.in.command.MemberInputPort;
import com.pixelo.health.wellplate.membership.application.in.command.RegisterMemberCommand;
import com.pixelo.health.wellplate.membership.domain.provider.MemberProviderImpl;
import com.pixelo.health.wellplate.membership.application.vo.MemberVo;
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
    public MemberVo registerMemberCommand(RegisterMemberCommand command) {
        var dto = CreateMemberDto.builder()
                .email(command.email())
                .password(command.password())
                .build();

        var member = MemberFactory.createMemberTypeOfWellnessChallenger(dto);
        var savedMember = MemberProviderImpl.from(memberOutputPort.save(member));
        return memberMapStruct.toMemberVo(savedMember);
    }
}
