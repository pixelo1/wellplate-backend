package com.pixelo.health.wellplate.myhealth.domain.diet;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

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
    protected FoodInfo() {
        this.foods = new ArrayList<>();
    }
}
