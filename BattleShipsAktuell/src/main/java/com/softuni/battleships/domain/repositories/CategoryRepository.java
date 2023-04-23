package com.softuni.battleships.domain.repositories;

import com.softuni.battleships.domain.entities.Category;
import com.softuni.battleships.domain.entities.CategoryType;
import com.softuni.battleships.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> findByName(CategoryType name);
}
