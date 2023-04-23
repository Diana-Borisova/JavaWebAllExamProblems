package com.example.battle_ship.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "categories")
public class Category extends  BaseEntity{

    @Column(nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private CategoryEnum name;

    @Column(nullable = true, columnDefinition = "TEXT")
    private String description;

    public Category() {
    }

    public CategoryEnum getName() {
        return name;
    }

    public Category setName(CategoryEnum name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Category setDescription(String description) {
        this.description = description;
        return this;
    }
}
