package com.pixelo.health.wellplate.membership.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;

import java.util.Collection;
import java.util.UUID;

@EqualsAndHashCode(of = {"wellnessChallengerId"}, callSuper = false)
@Entity
@NoArgsConstructor
@Table(name = "member", schema = "wellplate")
public class Member implements UserDetails{

    @Id
    private UUID memberId;

    private String email;

    private String password;

    @Comment("회원 타입")
    @Enumerated(EnumType.STRING)
    private MemberType memberType;

    @Builder
    public Member(String email,
                  String password,
                  MemberType memberType) {
        //todo 추후 개인정보는 piid만 넣을예정
        Assert.notNull(email, "이메일은 필수 입니다.");
        Assert.notNull(password, "비밀번호는 필수 입니다.");
        Assert.notNull(memberType, "회원 타입은 필수 입니다.");

        this.memberId = UUID.randomUUID();
        this.email = email;
        this.password = password;
        this.memberType = memberType;
    }

    public UUID memberId() {return memberId;}
    public String email() {return this.email;}
    public String password() {return this.password;}
    public MemberType memberType() {
        return this.memberType;
    }



    /**
     * authenticate를 위한 override
     * */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return memberType.getAuthorities();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
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
