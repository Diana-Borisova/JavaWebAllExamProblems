package com.spotifyplaylistapp.model.binding;


import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisterBindingModel {

    @NotNull(message = "Username cannot be empty!")
    @Size(min= 3, max= 20, message = "Username length must be between 3 and 20 characters!")
    private String username;

    @NotNull(message = "Password cannot be empty!")
    @Size(min= 3, max= 20,message = "Password length must be between 3 and 20 characters!")
    private String password;

    @Email
    @NotBlank(message = "Email cannot be empty!")
    private String email;

    @NotNull(message = "Password cannot be empty!")
    @Size(min= 3, max= 20,message = "Password length must be between 3 and 20 characters!")
    private String confirmPassword;
}
