package com.example.battle_ship.service.impl;

import com.example.battle_ship.model.entity.Category;
import com.example.battle_ship.model.entity.CategoryEnum;
import com.example.battle_ship.repository.CategoryRepository;
import com.example.battle_ship.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void initCategories() {
        if (this.categoryRepository.count() != 0){
            return;
        }

        Arrays.stream(CategoryEnum.values()).
                forEach(categoryEnum -> {
                    Category category = new Category();
                    category.setName(categoryEnum);
                    this.categoryRepository.save(category);
                });

    }

    @Override
    public Category findCategoryByName(CategoryEnum name) {
        return this.categoryRepository.findCategoryByName(name);
    }
}
