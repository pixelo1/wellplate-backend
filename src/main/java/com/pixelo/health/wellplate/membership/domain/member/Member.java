package com.pixelo.health.wellplate.membership.domain.member;

import com.pixelo.health.wellplate.membership.domain.member.event.EventType;
import com.pixelo.health.wellplate.membership.domain.member.event.MemberCreatedEvent;
import com.pixelo.health.wellplate.membership.domain.member.event.MemberEvent;
import com.pixelo.health.wellplate.membership.domain.member.event.Topic;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;

import java.util.Collection;
import java.util.UUID;


@EqualsAndHashCode(of = {"memberId"}, callSuper = false)
@Entity
@NoArgsConstructor
@Table(name = "member", schema = "wellplate")
public class Member implements UserDetails {

    @Id
    private UUID memberId;

    private String loginId;

    private String email;

    private String password;

    @Comment("회원 타입")
    @Enumerated(EnumType.STRING)
    private MemberType memberType;

    @Comment("회원가입 상태")
    @Enumerated(EnumType.STRING)
    @Column(name = "registration_status")
    private RegistrationStatus registrationStatus;

    @Builder
    public Member(String loginId,
                  String email,
                  String password,
                  MemberType memberType) {
        //todo 추후 개인정보는 piid만 넣을예정
        Assert.notNull(loginId, "아이디는 필수 입니다.");
        Assert.notNull(email, "이메일은 필수 입니다.");
        Assert.notNull(password, "비밀번호는 필수 입니다.");
        Assert.notNull(memberType, "회원 타입은 필수 입니다.");

        this.memberId = UUID.randomUUID();
        this.loginId = loginId;
        this.email = email;
        this.password = password;
        this.memberType = memberType;
        this.registrationStatus = RegistrationStatus.PENDING;
    }

    public UUID memberId() {return memberId;}
    public String loginId() {return this.loginId;}
    public String email() {return this.email;}
    public String password() {return this.password;}
    public MemberType memberType() {
        return this.memberType;
    }


    //todo event timeout 설정 필요
    public MemberEvent<MemberCreatedEvent> memberCreatedEvent() {
        MemberCreatedEvent memberCreatedEvent = MemberCreatedEvent.builder()
                .memberId(this.memberId)
                .loginId(this.loginId)
                .password(this.password)
                .memberType(this.memberType.code())
                .build();

        return MemberEvent.<MemberCreatedEvent>builder()
                .aggregateType(this.getClass().getSimpleName())
                .aggregateId(this.memberId)
                .topic(Topic.MEMBERSHIP_TOPIC)
                .eventType(EventType.MEMBER_CREATED)
                .payload(memberCreatedEvent)
                .build();
    }

    /**
     * authenticate를 위한 override
     * */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // 여러 권한을 가질 수 있으므로 Collection으로 반환
//        var authority = new SimpleGrantedAuthority();
//        var simpleGrantedAuthorities = new ArrayList<SimpleGrantedAuthority>();
//        simpleGrantedAuthorities.addAll();
        return memberType.getAuthorities();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return loginId;
    }

    /**
     * 계정 탈퇴, 비밀번호 변경, 휴면, 락 등 정책을 추가해주면 된다
     * */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
