package com.softuni.andrey.s.entity.services;

import com.softuni.andrey.s.entity.CategoryNameEnum;
import com.softuni.andrey.s.entity.GenderEnum;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemServiceModel {

    private String id;

    private String name;

    private String description;

    private BigDecimal price;

    private CategoryNameEnum category;

    private GenderEnum gender;
}
