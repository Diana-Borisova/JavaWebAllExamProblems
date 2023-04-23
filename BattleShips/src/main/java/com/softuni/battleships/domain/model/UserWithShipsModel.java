package com.softuni.battleships.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.*;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserWithShipsModel {

    private UserModel userModel;
    private List<ShipModel> shipModels;

}