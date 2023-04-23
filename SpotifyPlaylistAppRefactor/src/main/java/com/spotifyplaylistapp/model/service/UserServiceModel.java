package com.spotifyplaylistapp.model.service;

import com.spotifyplaylistapp.model.entity.BaseEntity;
import com.spotifyplaylistapp.model.entity.Song;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class UserServiceModel{

    private Long id;
    private String username;

    private String password;

    private String email;

    private String confirmPassword;
}
