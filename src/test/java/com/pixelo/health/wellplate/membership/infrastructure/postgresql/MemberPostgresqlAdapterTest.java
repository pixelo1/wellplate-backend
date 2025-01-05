package com.pixelo.health.wellplate.membership.infrastructure.postgresql;

import com.pixelo.health.wellplate.membership.domain.Member;
import com.pixelo.health.wellplate.membership.domain.MemberType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(MemberPostgresqlAdapter.class)
class MemberPostgresqlAdapterTest {

    @Autowired
    private MemberPostgresqlAdapter memberPostgresqlAdapter;

    @Test
    @DisplayName("회원 생성 테스트123")
    void save_Member() {
        String loginId = "test";
        String email = "test@naver.com";
        String password = "1234";

        var createdMember = Member.builder()
                .loginId(loginId)
                .email(email)
                .password(password)
                .memberType(MemberType.ROLE_WELLNESS_CHALLENGER)
                .build();
        var savedMember = memberPostgresqlAdapter.save(createdMember);

        Assertions.assertThat(savedMember).isNotNull();
        Assertions.assertThat(savedMember.memberId()).isNotNull();
        Assertions.assertThat(savedMember.email()).isEqualTo(email);
        Assertions.assertThat(savedMember.password()).isEqualTo(password);
        Assertions.assertThat(savedMember.memberType()).isEqualTo(MemberType.ROLE_WELLNESS_CHALLENGER);
    }
}