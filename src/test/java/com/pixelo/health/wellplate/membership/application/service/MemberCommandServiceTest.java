package com.pixelo.health.wellplate.membership.application.service;

import com.pixelo.health.wellplate.membership.application.in.command.RegisterMemberCommand;
import com.pixelo.health.wellplate.membership.application.out.EventOutputPort;
import com.pixelo.health.wellplate.membership.application.out.MemberOutputPort;
import com.pixelo.health.wellplate.membership.application.out.dto.ExternalEventDto;
import com.pixelo.health.wellplate.membership.application.vo.MemberVo;
import com.pixelo.health.wellplate.membership.domain.member.Member;
import com.pixelo.health.wellplate.membership.domain.member.domainservices.MemberFactory;
import com.pixelo.health.wellplate.membership.domain.member.domainservices.dtos.CreateMemberDto;
import com.pixelo.health.wellplate.membership.domain.member.provider.MemberProviderImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class MemberCommandServiceTest {
    @Mock
    private MemberOutputPort memberOutputPort;

    @Mock
    private MemberMapStruct memberMapStruct;

    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private EventOutputPort eventOutputPort;

    @InjectMocks
    private MemberCommandService memberCommandService;

    @Test
    @DisplayName("회원가입 - 일반 - 테스트")
    void registerMemberCommandService() {
        // given
        String loginId = "test";
        String email = "test@naver.com";
        String password = "1234";

        var command = RegisterMemberCommand.builder()
                .loginId(loginId)
                .email(email)
                .password(password)
                .build();

        var encodedPassword = new BCryptPasswordEncoder().encode(password);
        Mockito.when(passwordEncoder.encode(password))
                .thenReturn(encodedPassword);

        var createDto = CreateMemberDto.builder()
                .loginId(loginId)
                .email(email)
                .password(encodedPassword)
                .build();

        var createdMember = MemberFactory.createMemberTypeOfWellnessChallenger(createDto);
        Mockito.when(memberOutputPort.save(Mockito.any(Member.class)))
                .thenReturn(createdMember);


        var memberCreatedEvent = createdMember.memberCreatedEvent();
//        Mockito.doNothing().when(interalEventOutputPort).internalPublish(memberCreatedEvent);
        Mockito.doNothing().when(eventOutputPort).externalPublish(Mockito.any(ExternalEventDto.class));

        var expectedMemberVo = buildMemberVo(createdMember);
        Mockito.when(memberMapStruct.toMemberVo(Mockito.any(MemberProviderImpl.class)))
                .thenReturn(expectedMemberVo);

        //when
        var memberVo = memberCommandService.registerMemberCommand(command);
        //then
        Assertions.assertNotNull(memberVo);
        Assertions.assertEquals(expectedMemberVo, memberVo);

        verify(memberOutputPort).save(Mockito.any(Member.class));
        verify(memberMapStruct).toMemberVo(Mockito.any(MemberProviderImpl.class));
    }

    private static MemberVo buildMemberVo(Member createdMember) {
        return MemberVo.builder()
                .memberId(createdMember.memberId())
                .email(createdMember.email())
                .password(createdMember.password())
                .memberType(createdMember.memberType().code())
                .build();
    }
}