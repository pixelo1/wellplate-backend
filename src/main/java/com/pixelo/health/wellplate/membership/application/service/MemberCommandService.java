package com.pixelo.health.wellplate.membership.application.service;

import com.pixelo.health.wellplate.membership.application.in.command.MemberCommandInputPort;
import com.pixelo.health.wellplate.membership.application.in.command.RegisterMemberCommand;
import com.pixelo.health.wellplate.membership.application.out.EventOutputPort;
import com.pixelo.health.wellplate.membership.application.out.dto.ExternalEventDto;
import com.pixelo.health.wellplate.membership.application.vo.MemberVo;
import com.pixelo.health.wellplate.membership.domain.member.event.MemberCreatedEvent;
import com.pixelo.health.wellplate.membership.domain.member.event.MemberEvent;
import com.pixelo.health.wellplate.membership.domain.member.provider.MemberProviderImpl;
import com.pixelo.health.wellplate.membership.application.out.MemberOutputPort;
import com.pixelo.health.wellplate.membership.domain.member.domainservices.MemberFactory;
import com.pixelo.health.wellplate.membership.domain.member.domainservices.dtos.CreateMemberDto;
import com.pixelo.health.wellplate.membership.infrastructure.event.kafka.KafkaEventType;
import com.pixelo.health.wellplate.membership.infrastructure.event.kafka.KafkaTopic;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberCommandService implements MemberCommandInputPort {
    private final MemberOutputPort memberOutputPort;
    private final MemberMapStruct memberMapStruct;
    private final PasswordEncoder passwordEncoder;
    private final EventOutputPort interalEventOutputPort;
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

        var memberRegisteredEvent = savedMember.memberCreatedEvent();
//        interalEventOutputPort.internalPublish(memberRegisteredEvent);
        var externalEventDto = parserExternalEventDto(memberRegisteredEvent);
        interalEventOutputPort.externalPublish(externalEventDto);

        var memberProvider = MemberProviderImpl.from(savedMember);
        return memberMapStruct.toMemberVo(memberProvider);
    }

    private ExternalEventDto<Object> parserExternalEventDto(MemberEvent<?> memberRegisteredEvent) {
        return ExternalEventDto.builder()
                .aggregateType(memberRegisteredEvent.aggregateType())
                .aggregateId(memberRegisteredEvent.aggregateId())
                .topic(memberRegisteredEvent.topic())
                .eventType(memberRegisteredEvent.eventType())
                .payload(memberRegisteredEvent.payload())
                .build();
    }

}
