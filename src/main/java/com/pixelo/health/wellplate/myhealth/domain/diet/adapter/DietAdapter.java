package com.pixelo.health.wellplate.myhealth.domain.diet.adapter;

import com.pixelo.health.wellplate.myhealth.domain.diet.Diet;
import lombok.Builder;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class DietAdapter {
    private Diet diet;

    private List<FoodAdapter> foodAdapters;

    @Builder
    public DietAdapter(Diet diet) {
        this.diet = diet;
        this.foodAdapters = diet.foodInfo().foods().stream()
                .map(FoodAdapter::of)
                .toList();
    }

    public UUID getDietId() {
        return diet.dietId();
    }
    public UUID getHealthId() {
        return diet.healthId();
    }
    public UUID getWellnessChallengerId() {
        return diet.wellnessChallengerId();
    }
    public LocalDate getMealTime() {
        return diet.mealTime().date();
    }

    public List<FoodAdapter> getFoodAdapters() {
        return foodAdapters;
    }
}
