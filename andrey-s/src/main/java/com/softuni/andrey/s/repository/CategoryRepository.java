package com.softuni.andrey.s.repository;

import com.softuni.andrey.s.entity.Category;
import com.softuni.andrey.s.entity.CategoryNameEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category,String> {

    Optional<Category> findByCategoryNameEnum(CategoryNameEnum name);
}
