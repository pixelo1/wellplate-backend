package com.pixelo.health.wellplate.myhealth.domain.diet;

import com.pixelo.health.wellplate.myhealth.domain.diet.valueobjects.Food;
import com.pixelo.health.wellplate.myhealth.domain.diet.valueobjects.FoodInfo;
import com.pixelo.health.wellplate.myhealth.domain.share.Date;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@NoArgsConstructor
@EqualsAndHashCode(of = "dietId")
@Table(name = "diet", schema = "wellplate")
public class Diet {

    @Id
    @Column(name = "diet_id")
    private UUID dietId;
    @Column(name = "health_id")
    private UUID healthId;
    @Column(name = "wellness_challenger_id")
    private UUID wellnessChallengerId;

    /** Food - Core api 가 들어올 예정*/
    @Comment("음식 정보")
    @Column(name = "food_info")
    @JdbcTypeCode(SqlTypes.JSON)
    private FoodInfo foodInfo;

//    @Embedded
    @Comment(value = "섭취 시간")
//    @AttributeOverride(name = "date", column = @Column(name = "meal_time"))
    @Column(name = "meal_time")
    private LocalDateTime mealTime;

    @Builder
    protected Diet(UUID healthId,
                UUID wellnessChallengerId,
                LocalDateTime mealTime) {
        Assert.notNull(healthId, "건강 정보 ID는 필수 입니다.");
        Assert.notNull(wellnessChallengerId, "이용자 ID는 필수 입니다.");
        Assert.notNull(mealTime, "섭취 시간은 는 필수 입니다.");

        this.dietId = UUID.randomUUID();
        this.healthId = healthId;
        this.wellnessChallengerId = wellnessChallengerId;
        this.foodInfo = new FoodInfo();
        this.mealTime = mealTime;
    }

    public void updateFoodInfo(List<Food> foods) {
        Assert.notEmpty(foods, "음식정보는 필수 입니다.");
        this.foodInfo = FoodInfo.builder()
                .foods(foods)
                .build();
    }


    public UUID dietId() {
        return this.dietId;
    }
    public UUID healthId() {
        return this.healthId;
    }
    public UUID wellnessChallengerId() {
        return this.wellnessChallengerId;
    }
    public FoodInfo foodInfo() {
        return this.foodInfo;
    }
    public LocalDateTime mealTime() {
        return this.mealTime;
    }


}
