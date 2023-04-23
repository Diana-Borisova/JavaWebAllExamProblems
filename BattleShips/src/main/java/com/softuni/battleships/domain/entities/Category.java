package com.softuni.battleships.domain.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "categories")
public class Category extends BaseEntity {
    @Column(nullable = false, unique = true)
    @Enumerated(EnumType.ORDINAL)
    private CategoryType name;

    @Column(columnDefinition = "TEXT",nullable = true)
    private String description;
}

