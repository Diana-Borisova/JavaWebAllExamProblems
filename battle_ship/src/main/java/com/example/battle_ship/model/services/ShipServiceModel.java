package com.example.battle_ship.model.services;

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
public class ShipServiceModel {


    private String name;


    private Long health;


    private Long power;


    private LocalDate created;


    private CategoryEnum category;

}
