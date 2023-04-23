package com.softuni.battleships.domain.model;

import com.softuni.battleships.validations.CheckUserExistence.CheckUserExistence;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserModel {
    private Long id;

    private String username;


    private String fullName;


    private String email;


    private String password;


    private String confirmPassword;
}
