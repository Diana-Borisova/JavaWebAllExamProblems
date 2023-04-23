package com.example.battle_ship.service;

import com.example.battle_ship.model.entity.Category;
import com.example.battle_ship.model.entity.CategoryEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface CategoryService{

    void initCategories();

    Category findCategoryByName(CategoryEnum name);
}
