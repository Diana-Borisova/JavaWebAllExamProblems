package com.softuni.andrey.s.entity.binding;

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
public class ItemAddBindingModel {


    @NotNull(message = "Incorrect name!")
    @Size(min=2, message = "Incorrect name!")
    private String name;

    @NotNull(message = "Incorrect description!")
    @Size(min=3, message = "Incorrect description!")
    private String description;

    @NotNull
    @Positive(message = "Incorrect price!")
    private BigDecimal price;

    @NotNull
    @Enumerated(EnumType.STRING)
    private CategoryNameEnum category;

    @NotNull
    @Enumerated(EnumType.STRING)
    private GenderEnum gender;
}
