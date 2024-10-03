package com.pixelo.health.wellplate.myhealth.domain.diet.valueobjects;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.util.Comparator;

@EqualsAndHashCode(of = {"name"}, callSuper = false)
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY,
        getterVisibility = JsonAutoDetect.Visibility.NONE,
        isGetterVisibility = JsonAutoDetect.Visibility.NONE)
public class Food {

    private String name;
    private BigDecimal calorie;

    @Builder
    public Food(String name,
                BigDecimal calorie) {
        Assert.hasText(name, "음식명은 필수 입니다.");
        Assert.notNull(calorie, "칼로리는 필수 입니다.");
        this.name = name;
        this.calorie = calorie;
    }

    public String name() {
        return this.name;
    }
    public BigDecimal calorie() {
        return this.calorie;
    }
}
