package com.pixelo.health.wellplate.membership.application.service;

import com.pixelo.health.wellplate.membership.application.in.command.MemberCommandInputPort;
import com.pixelo.health.wellplate.membership.application.in.command.RegisterMemberCommand;
import com.pixelo.health.wellplate.membership.application.out.InternalEventOutputPort;
import com.pixelo.health.wellplate.membership.application.vo.MemberVo;
import com.pixelo.health.wellplate.membership.domain.provider.MemberProviderImpl;
import com.pixelo.health.wellplate.membership.application.out.MemberOutputPort;
import com.pixelo.health.wellplate.membership.domain.domainservices.MemberFactory;
import com.pixelo.health.wellplate.membership.domain.domainservices.dtos.CreateMemberDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberCommandService implements MemberCommandInputPort {
    private final MemberOutputPort memberOutputPort;
    private final MemberMapStruct memberMapStruct;
    private final PasswordEncoder passwordEncoder;
    private final InternalEventOutputPort interalEventOutputPort;
    @Override
    public MemberVo registerMemberCommand(RegisterMemberCommand command) {
        String encodedPassword = passwordEncoder.encode(command.password());
        var dto = CreateMemberDto.builder()
                .loginId(command.loginId())
                .email(command.email())
                .password(encodedPassword)
                .build();

        var member = MemberFactory.createMemberTypeOfWellnessChallenger(dto);
        var savedMember = memberOutputPort.save(member);

        var memberRegisteredEvent = savedMember.toMemberRegisteredEvent();
        interalEventOutputPort.publish(memberRegisteredEvent);

        var memberProvider = MemberProviderImpl.from(savedMember);
        return memberMapStruct.toMemberVo(memberProvider);
    }

}
