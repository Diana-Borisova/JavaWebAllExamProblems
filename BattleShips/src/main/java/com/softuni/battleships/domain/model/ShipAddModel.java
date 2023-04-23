package com.softuni.battleships.domain.model;

import com.softuni.battleships.domain.entities.CategoryType;

import com.softuni.battleships.validations.CheckShipExistence.CheckShipExistence;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor

public class ShipAddModel {

    @NotNull
    @Size(min = 2, max = 10)
    @CheckShipExistence
    private String name;

    @NotNull
    @Positive
    private Long health;

    @NotNull
    @Positive
    private Long power;

    @NotNull
    @PastOrPresent
    private LocalDate created;

    @NotNull
    private CategoryType category;

}
