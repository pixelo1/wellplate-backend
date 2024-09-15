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
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(MemberPostgresqlAdapter.class)
class MemberPostgresqlAdapterTest {

    @Container
    private static final PostgreSQLContainer<?> postgresContainer = new PostgreSQLContainer<>("postgres:16.3")
            .withDatabaseName("testdb")
            .withUsername("user")
            .withPassword("1234");

    @Autowired
    private MemberPostgresqlAdapter memberPostgresqlAdapter;

    @Test
    @DisplayName("회원 생성 테스트")
    void save_Member() {
        String email = "test@naver.com";
        String password = "1234";

        var createdMember = Member.builder()
                .email(email)
                .password(password)
                .memberType(MemberType.WELLNESS_CHALLENGER)
                .build();
        var savedMember = memberPostgresqlAdapter.save(createdMember);

        Assertions.assertThat(savedMember).isNotNull();
        Assertions.assertThat(savedMember.memberId()).isNotNull();
        Assertions.assertThat(savedMember.email()).isEqualTo(email);
        Assertions.assertThat(savedMember.password()).isEqualTo(password);
        Assertions.assertThat(savedMember.memberType()).isEqualTo(MemberType.WELLNESS_CHALLENGER);
    }
}