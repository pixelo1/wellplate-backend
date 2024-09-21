package com.pixelo.health.wellplate.myhealth.domain.health;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Embeddable
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY,
        getterVisibility = JsonAutoDetect.Visibility.NONE,
        isGetterVisibility = JsonAutoDetect.Visibility.NONE)
@SuppressWarnings("JpaDataSourceORMInspection")
public class BodyWeight {
    private static final String UNIT = "kg";
    private BigDecimal weight;
    private String measurementUnit;

    @Builder
    public BodyWeight(BigDecimal weight) {
        this.weight = validationWeight(weight);
        this.measurementUnit = UNIT;
    }

    private BigDecimal validationWeight(BigDecimal weight) {
        if (weight == null) {
            return BigDecimal.ZERO;
        }
        return weight;
    }

    public BigDecimal weight() {
        return this.weight;
    }

    public String measurementUnit() {
        return this.measurementUnit;
    }
}
