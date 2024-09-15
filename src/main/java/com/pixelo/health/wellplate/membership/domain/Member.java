package com.pixelo.health.wellplate.membership.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@NoArgsConstructor
@Table(name = "member", schema = "wellplate")
public class Member {

    @Id
    private UUID memberId;

    private String email;

    private String password;

    @Builder
    public Member(String email, String password) {
        this.memberId = UUID.randomUUID();
        this.email = email;
        this.password = password;
    }

    public UUID memberId() {return memberId;}
    public String email() {return this.email;}
    public String password() {return this.password;}
}
