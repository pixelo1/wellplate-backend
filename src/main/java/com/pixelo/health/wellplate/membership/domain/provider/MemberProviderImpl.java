package com.pixelo.health.wellplate.membership.domain.provider;

import com.pixelo.health.wellplate.membership.domain.Member;
import com.pixelo.health.wellplate.membership.domain.MemberType;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.UUID;


//todo 해당 형태 다시 고민해보기
@Deprecated
@RequiredArgsConstructor(staticName = "from")
public class MemberProviderImpl implements MemberEntityProvider {

//    private final Supplier<Member> memberLoader;
    private final Member member;


    public UUID getMemberId() {
        return member.memberId();
    }

    public String getLoginId() {
        return member.loginId();
    }

    public String getEmail() {
        return member.email();
    }

    public String getPassword() {
        return member.password();
    }

    public MemberType getMemberType() {
        return member.memberType();
    }

}
