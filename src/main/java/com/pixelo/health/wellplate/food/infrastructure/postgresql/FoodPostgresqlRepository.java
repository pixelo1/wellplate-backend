package com.pixelo.health.wellplate.food.infrastructure.postgresql;

import com.pixelo.health.wellplate.food.domain.food.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodPostgresqlRepository extends JpaRepository<Food, String> {

    List<Food> findByFoodNameContains(String foodName);
}
