package com.example.pathfinder.model.binding;


import com.example.pathfinder.model.validation.EmailValidator.UniqueUserEmail;
import com.example.pathfinder.model.validation.FieldMatch;
import com.example.pathfinder.model.validation.UsernameValidator.UniqueUsername;

import javax.validation.constraints.*;

@FieldMatch(
        first = "password",
        second = "confirmPassword",
        message = "Passwords do not match"
)
public class UserRegisterBindingModel {

    @NotNull
    private Long id;
    @Size(min =3, message = "Username length must be more than 3 characters")
    @NotEmpty
    @UniqueUsername(message =  "Username is already occupied!")
    private String username;

    @NotNull
    @Size(min =3, message = "Full name length must be more than 3 characters")
    private String fullName;

    @NotNull(message = "Must be valid age")
    @Min(value = 0, message = "Must be valid age")
    @Max(value = 90, message = "Must be valid age")
    private Integer age;

    @NotNull
    @Size(min = 5, max = 20, message = " Password length must be between 5 and 20 characters and passwords should match.")
    private String password;

    @NotNull
    @Size(min = 5, max = 20, message = " Password length must be between 5 and 20 characters and passwords should match.")
    private String confirmPassword;

    @Email(message = "Must be valid email")
    @UniqueUserEmail(message = "User email must be unique!")
    private String email;

    public String getUsername() {
        return username;
    }

    public UserRegisterBindingModel setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getFullName() {
        return fullName;
    }

    public UserRegisterBindingModel setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public Integer getAge() {
        return age;
    }

    public UserRegisterBindingModel setAge(Integer age) {
        this.age = age;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserRegisterBindingModel setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public UserRegisterBindingModel setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserRegisterBindingModel setEmail(String email) {
        this.email = email;
        return this;
    }
}
