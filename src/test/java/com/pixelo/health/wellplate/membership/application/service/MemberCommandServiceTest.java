package com.pixelo.health.wellplate.membership.application.service;

import com.pixelo.health.wellplate.membership.application.in.command.RegisterMemberCommand;
import com.pixelo.health.wellplate.membership.application.out.MemberOutputPort;
import com.pixelo.health.wellplate.membership.application.vo.MemberVo;
import com.pixelo.health.wellplate.membership.domain.Member;
import com.pixelo.health.wellplate.membership.domain.domainservices.MemberFactory;
import com.pixelo.health.wellplate.membership.domain.domainservices.dtos.CreateMemberDto;
import com.pixelo.health.wellplate.membership.domain.provider.MemberProviderImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class MemberCommandServiceTest {
    @Mock
    private MemberOutputPort memberOutputPort;

    @Mock
    private MemberMapStruct memberMapStruct;

    @InjectMocks
    private MemberCommandService memberCommandService;

    @Test
    @DisplayName("회원가입 - 일반 - 테스트")
    void registerMemberCommandService() {
        // given
        String email = "test@naver.com";
        String password = "1234";

        var command = RegisterMemberCommand.builder()
                .email(email)
                .password(password)
                .build();
        var createDto = CreateMemberDto.builder()
                .email(email)
                .password(password)
                .build();
        var createdMember = MemberFactory.createMemberTypeOfWellnessChallenger(createDto);
        Mockito.when(memberOutputPort.save(Mockito.any(Member.class))).thenReturn(createdMember);

        var createdMemberAdapter = MemberProviderImpl.from(createdMember);

        var expectedMemberVo = buildMemberVo(createdMember);
        Mockito.when(memberMapStruct.toMemberVo(Mockito.any(MemberProviderImpl.class))).thenReturn(expectedMemberVo);

        //when
        var resultMemberVo = memberCommandService.registerMemberCommand(command);
        //then
        Assertions.assertNotNull(resultMemberVo);
        Assertions.assertEquals(expectedMemberVo, resultMemberVo);

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