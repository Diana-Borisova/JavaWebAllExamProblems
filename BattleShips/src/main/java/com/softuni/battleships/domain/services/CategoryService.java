package com.softuni.battleships.domain.services;

import com.softuni.battleships.domain.entities.Category;
import com.softuni.battleships.domain.entities.CategoryType;
import com.softuni.battleships.domain.entities.User;
import com.softuni.battleships.domain.model.CategoryModel;
import com.softuni.battleships.domain.model.UserModel;
import com.softuni.battleships.domain.repositories.CategoryRepository;
import jakarta.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.stream.DoubleStream;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;


    @Autowired
    public CategoryService(CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    private void postConstruct(){
        if (this.categoryRepository.count()== 0){
            this.categoryRepository.saveAllAndFlush(Arrays.stream(CategoryType.values())
                    .map(ct -> CategoryModel.builder()
                            .name(ct)
                            .description("fight me")
                            .build())
                    .map(ctm -> this.modelMapper.map(ctm, Category.class))
                    .toList());

        }
    }

    public CategoryModel findByName(CategoryType name){
        return this.modelMapper.map(this.categoryRepository.findByName(name).orElseThrow(), CategoryModel.class);
    }
}
