package com.example.battle_ship.model.Dtos;

import com.example.battle_ship.validations.UniqueUsername;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class UserRegisterDto {


    @NotBlank
    @UniqueUsername(message = "Username must be unique!")
    @Size(min = 3, max = 10, message = "Username length must be between 3 and 10 characters.")
    private String username;

    @NotBlank
    @Size(min = 5, max = 20,message ="Full name length must be between 5 and 20 characters.")
    private String fullName;


    @Email(message = "Enter valid email address.")
    @NotBlank
    private String email;


    @NotNull
    @Size(min = 3, message = "Password length must be long than 3 characters.")
    private String password;


    @NotNull
    @Size(min = 3, message = "Password length must be long than 3 characters.")
    private String confirmPassword;


}
