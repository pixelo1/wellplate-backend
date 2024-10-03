package com.pixelo.health.wellplate.myhealth.domain.health;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.springframework.util.Assert;

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

    @Column(name = "wellness_challenger_id")
    private UUID wellnessChallengerId;

    @Embedded
    @Column(name = "base_body_weight")
    @AttributeOverrides({
            @AttributeOverride(name = "weight", column = @Column(name = "base_body_weight")),
            @AttributeOverride(name = "measurementUnit", column = @Column(name = "base_measurement_unit"))
    })
    private BodyWeight baseBodyWeight;

    @Embedded
    @Column(name = "goal_body_weight")
    @AttributeOverrides({
            @AttributeOverride(name = "weight", column = @Column(name = "goal_body_weight")),
            @AttributeOverride(name = "measurementUnit", column = @Column(name = "goal_measurement_unit"))
    })
    private BodyWeight goalBodyWeight;

    @Builder
    public Health(UUID wellnessChallengerId,
                  BigDecimal baseBodyWeight,
                  BigDecimal goalBodyWeight) {
        Assert.notNull(wellnessChallengerId, "이용자 ID는 필수 입니다.");
        Assert.notNull(baseBodyWeight, "현재 몸무게는 필수 입니다.");
        Assert.notNull(goalBodyWeight, "목표 몸무게는 필수 입니다.");

        this.healthId = UUID.randomUUID();
        this.wellnessChallengerId = wellnessChallengerId;
        this.baseBodyWeight = BodyWeight.builder()
                .weight(baseBodyWeight)
                .build();
        this.goalBodyWeight = BodyWeight.builder()
                .weight(goalBodyWeight)
                .build();
    }

    public UUID healthId() {
        return this.healthId;
    }

    public UUID wellnessChallengerId() {
        return this.wellnessChallengerId;
    }

    public BigDecimal baseBodyWeight() {
        return this.baseBodyWeight.weight();
    }

    public String baseMeasurementUnit() {
        return this.baseBodyWeight.measurementUnit();
    }

    public BigDecimal goalBodyWeight() {
        return this.goalBodyWeight.weight();
    }

    public String goalMeasurementUnit() {
        return this.goalBodyWeight.measurementUnit();
    }
}
