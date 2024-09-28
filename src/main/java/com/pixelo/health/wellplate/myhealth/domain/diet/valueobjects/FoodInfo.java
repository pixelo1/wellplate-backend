package com.pixelo.health.wellplate.myhealth.domain.diet.valueobjects;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY,
        getterVisibility = JsonAutoDetect.Visibility.NONE,
        isGetterVisibility = JsonAutoDetect.Visibility.NONE)
public class FoodInfo {

    private List<Food> foods = new ArrayList<>();

    @Builder
    public FoodInfo(List<Food> foods) {
        this.foods = foods;
    }
    public FoodInfo() {
        this.foods = new ArrayList<>();
    }

    public List<Food> foods() {
        return this.foods;
    }
}
