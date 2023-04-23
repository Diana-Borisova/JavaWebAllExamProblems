package com.softuni.andrey.s.service.impl;

import com.softuni.andrey.s.entity.Category;
import com.softuni.andrey.s.entity.CategoryNameEnum;
import com.softuni.andrey.s.repository.CategoryRepository;
import com.softuni.andrey.s.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void initCategory() {
        if (categoryRepository.count() != 0){
            return;
        }

        Arrays.stream(CategoryNameEnum.values())
                .forEach(categoryNameEnum -> {
                    Category category = new Category();
                    category.setCategoryNameEnum(categoryNameEnum);
                    categoryRepository.save(category);
                });

    }

    @Override
    public Category findByName(CategoryNameEnum name) {
        return this.categoryRepository.findByCategoryNameEnum(name).orElse(null);
    }
}
