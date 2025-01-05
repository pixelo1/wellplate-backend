package com.pixelo.health.wellplate.authentication.application.service;

import com.pixelo.health.wellplate.authentication.application.in.MemberCommandInputPort;
import com.pixelo.health.wellplate.authentication.application.in.command.member.SaveMemberCommand;
import com.pixelo.health.wellplate.authentication.application.out.AuthMemberOutputPort;
import com.pixelo.health.wellplate.authentication.domain.member.AuthMember;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthMemberCommandService implements MemberCommandInputPort {

    private final AuthMemberOutputPort authMemberOutputPort;

    @Override
    public void saveMember(SaveMemberCommand command) {
        AuthMember authMember = AuthMember.builder()
                .memberId(command.memberId())
                .loginId(command.loginId())
                .password(command.password())
                .memberType(command.memberType())
                .build();
        authMemberOutputPort.save(authMember);
    }
}
