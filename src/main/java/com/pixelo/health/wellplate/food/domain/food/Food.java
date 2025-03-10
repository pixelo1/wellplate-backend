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

    @Column(name = "nutrition_base_amount")
    @Comment("영양소 기준량")
    private BigDecimal nutritionBaseAmount;

    @Column(name = "nutrition_base_unit")
    @Comment("영양소 기준 단위")
    private String nutritionBaseUnit;

    public String foodCode() {
        return this.foodCode;
    }
    public String foodName() {
        return this.foodName;
    }
    public BigDecimal calorie() {
        return this.calorie;
    }
    public BigDecimal size() {
        return this.size;
    }
    public String sizeUnit() {
        return this.sizeUnit;
    }
    public String makerName() {
        return this.makerName;
    }
}
