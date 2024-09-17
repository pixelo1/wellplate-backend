package com.pixelo.health.wellplate.authentication.domain.token;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "token", schema = "public")
@NoArgsConstructor
public class Token {

    @Id
    @Column(name = "token_id")
    private UUID tokenId;

    @Column(unique = true)
    private String token;

    @Enumerated(EnumType.STRING)
    private TokenType tokenType = TokenType.BEARER;

    private boolean revoked;

    private boolean expired;

    @Column(name = "member_id")
    private UUID memberId;

    @Builder
    public Token(String token,
                 UUID memberId) {
        this.tokenId = UUID.randomUUID();
        this.token = token;
        this.tokenType = TokenType.BEARER;
        this.memberId = memberId;
        this.revoked = false;
        this.expired = false;
    }

    public void updateRevokeToken() {
        this.revoked = true;
        this.expired = true;
    }

    public Boolean validaToken() {
        return !notValidToken();
    }

    private Boolean notValidToken() {
        return this.revoked || this.expired;
    }
}
