package com.pixelo.health.wellplate.health.domain.health;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@NoArgsConstructor
@EqualsAndHashCode(of = "healthId")
@Table(name = "health", schema = "wellplate")
@SuppressWarnings("JpaDataSourceORMInspection")
public class Health {

    @Id
    @Column(name = "health_id")
    private UUID healthId;

    @Embedded
    @Column(name = "base_body_weight")
    @JdbcTypeCode(SqlTypes.JSON)
    private BodyWeight baseBodyWeight;

    @Embedded
    @Column(name = "goal_body_weight")
    @JdbcTypeCode(SqlTypes.JSON)
    private BodyWeight goalBodyWeight;

    public Health(BigDecimal baseBodyWeight,
                  BigDecimal goalBodyWeight) {
        this.healthId = UUID.randomUUID();
        this.baseBodyWeight = BodyWeight.builder()
                .weight(baseBodyWeight)
                .build();
        this.goalBodyWeight = BodyWeight.builder()
                .weight(goalBodyWeight)
                .build();
    }


}
