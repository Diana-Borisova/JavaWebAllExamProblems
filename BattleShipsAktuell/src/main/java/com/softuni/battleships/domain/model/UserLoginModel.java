package com.softuni.battleships.domain.model;

import com.softuni.battleships.validations.CheckUserExistence.CheckUserExistence;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@CheckUserExistence
public class UserLoginModel {
    private String username;
    private String password;
}
