package com.example.battle_ship.model.Dtos;

import com.example.battle_ship.validations.UniqueUsername;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserLoginDto {

    @NotBlank
    @UniqueUsername(message = "Username must be unique!")
    @Size(min = 3, max = 10, message = "Username length must be between 3 and 10 characters.")
    private String username;
    @NotNull
    @Size(min = 3, message = "Password length must be long than 3 characters.")
    private String password;

}