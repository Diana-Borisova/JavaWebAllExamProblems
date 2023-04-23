package com.softuni.andrey.s.service;

import com.softuni.andrey.s.entity.Category;
import com.softuni.andrey.s.entity.CategoryNameEnum;

public interface CategoryService {
    void initCategory();
    Category findByName(CategoryNameEnum name);
}
