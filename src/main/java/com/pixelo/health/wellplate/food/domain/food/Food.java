package com.pixelo.health.wellplate.food.domain.food;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import java.math.BigDecimal;

@Table(name = "food", schema = "wellplate")
@Entity
@NoArgsConstructor
public class Food {

    @Id
    @Column(name = "food_code")
    @Comment("식품 코드")
    private String foodCode;

    @Column(name = "foodName")
    @Comment("식품명")
    private String foodName;

    @Column(name = "calorie")
    @Comment("식품 열량(kcal)")
    private BigDecimal calorie;

    @Column(name = "size")
    @Comment("식품중량")
    private BigDecimal size;

    @Column(name = "size_unit")
    @Comment("식품중량단위")
    private String sizeUnit;

    @Column(name = "maker_name")
    @Comment("제조사명")
    private String makerName;
}
