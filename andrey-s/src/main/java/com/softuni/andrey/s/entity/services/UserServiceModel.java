package com.softuni.andrey.s.entity.services;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class UserServiceModel {

    private String id;

    private String username;

    private String password;

    private String confirmPassword;

    private String email;

    private BigDecimal budget;
}
