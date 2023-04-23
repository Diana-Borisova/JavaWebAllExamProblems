package com.softuni.battleships.domain.model;

import com.softuni.battleships.domain.entities.CategoryType;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryModel {
    private Long id;
    private CategoryType name;
    private String description;


}
