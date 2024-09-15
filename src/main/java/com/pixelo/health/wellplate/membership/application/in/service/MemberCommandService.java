package com.pixelo.health.wellplate.membership.application.in.service;

import com.pixelo.health.wellplate.membership.application.in.command.MemberInputPort;
import com.pixelo.health.wellplate.membership.application.in.command.RegisterMemberCommand;
import com.pixelo.health.wellplate.membership.application.in.vo.MemberVo;
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
    private final MemberVoConverter memberVoConverter;
    @Override
    public MemberVo registerMemberCommand(RegisterMemberCommand command) {
        var dto = CreateMemberDto.builder()
                .email(command.email())
                .password(command.password())
                .build();

        var member = MemberFactory.createMember(dto);
        var savedMember = memberOutputPort.save(member);
        return memberVoConverter.toMemberVo(savedMember);
    }
}
