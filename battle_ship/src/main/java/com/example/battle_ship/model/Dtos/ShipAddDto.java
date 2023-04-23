package com.example.battle_ship.model.Dtos;

import com.example.battle_ship.model.entity.CategoryEnum;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor

public class ShipAddDto {

    @NotNull
    @Size(min = 2, max = 10, message = "Name must be between 2 and 10 characters.")
    private String name;

    @NotNull
    @Positive(message = "The health must be positive.")
    private Long health;

    @NotNull
    @Positive(message = "The power must be positive.")
    private Long power;

    @NotNull
    @PastOrPresent(message = "Created date cannot be in the future.")
    private LocalDate created;

    @NotNull(message = " You must select the category.")
    private CategoryEnum category;

}
