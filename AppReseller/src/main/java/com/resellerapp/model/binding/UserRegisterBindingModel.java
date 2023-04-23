package com.resellerapp.model.binding;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisterBindingModel {

    @NotNull(message = "Username cannot be empty!")
    @Size(min= 3, max= 20, message ="Username length must be between 3 and 20 characters (inclusive of 3 and 20).")
    private String username;

    @NotNull(message = "Password cannot be empty!")
    @Size(min= 3, max= 20,message = "Password length must be between 3 and 20 characters (inclusive of 3 and 20).")
    private String password;

    @Email
    @NotBlank(message = "Email cannot be empty!")
    private String email;


    @NotNull(message = "Password cannot be empty!")
    @Size(min= 3, max= 20,message = "Password length must be between 3 and 20 characters (inclusive of 3 and 20).")
    private String confirmPassword;
}
