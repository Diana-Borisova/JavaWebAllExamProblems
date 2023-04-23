package com.softuni.battleships.domain.model;
import com.softuni.battleships.validations.CheckShipExistence.CheckShipExistence;
import lombok.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShipModel {
    private Long id;
    private String name;
    private Long health;
    private Long power;
    private LocalDate created;
    private CategoryModel category;
    private UserModel user;


}
