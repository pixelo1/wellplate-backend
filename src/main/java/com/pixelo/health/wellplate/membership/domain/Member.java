package com.pixelo.health.wellplate.membership.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;
import org.springframework.util.Assert;

import java.util.UUID;

@EqualsAndHashCode(of = {"memberId"}, callSuper = false)
@Entity
@NoArgsConstructor
@Table(name = "member", schema = "wellplate")
public class Member {

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
}
