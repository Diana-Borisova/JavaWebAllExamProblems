package com.example.battle_ship.repository;

import com.example.battle_ship.model.entity.Category;
import com.example.battle_ship.model.entity.CategoryEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category findCategoryByName(CategoryEnum name);
}
