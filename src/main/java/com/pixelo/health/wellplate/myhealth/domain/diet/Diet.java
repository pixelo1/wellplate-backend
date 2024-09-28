package com.pixelo.health.wellplate.myhealth.domain.diet;

import com.pixelo.health.wellplate.myhealth.domain.share.Date;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.sql.JDBCType;
import java.time.LocalDate;
import java.util.ArrayList;
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

    @Embedded
    @Comment(value = "섭취 시간")
    @AttributeOverride(name = "date", column = @Column(name = "meal_time"))
    private Date mealTime;

    @Builder
    public Diet(UUID healthId,
                UUID wellnessChallengerId,
                LocalDate mealTime) {
        this.dietId = UUID.randomUUID();
        this.healthId = healthId;
        this.wellnessChallengerId = wellnessChallengerId;

        this.foodInfo = new FoodInfo();

        this.mealTime = Date.builder()
                .date(mealTime)
                .build();
    }

    public void updateFoodInfo(List<Food> foods) {
        this.foodInfo = FoodInfo.builder()
                .foods(foods)
                .build();
    }
}
