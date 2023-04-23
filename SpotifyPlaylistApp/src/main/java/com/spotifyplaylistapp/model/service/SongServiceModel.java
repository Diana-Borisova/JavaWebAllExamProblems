package com.spotifyplaylistapp.model.service;

import com.spotifyplaylistapp.model.entity.StyleEnum;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SongServiceModel {

    private Long id;

    private String performer;


    private String title;


    private int duration;

    private LocalDate releaseDate;

    private StyleEnum style;

}
