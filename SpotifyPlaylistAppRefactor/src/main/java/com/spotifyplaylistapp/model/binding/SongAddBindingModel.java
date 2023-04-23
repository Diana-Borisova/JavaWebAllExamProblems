package com.spotifyplaylistapp.model.binding;

import com.spotifyplaylistapp.model.entity.Style;
import com.spotifyplaylistapp.model.entity.StyleEnum;
import com.spotifyplaylistapp.model.entity.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SongAddBindingModel {

    @NotNull
    @Size(min= 3, max = 20, message = "Performer name length must be between 3 and 20 characters (inclusive of 3 and 20).")
    private String performer;

    @NotNull
    @Size(min= 2, max = 20, message = "Title length must be between 2 and 20 characters (inclusive of 2 and 20).")
    private String title;

    @NotNull
    @Positive(message = "Duration must be positive!")
    private int duration;

    @NotNull
    @PastOrPresent(message = "The date cannot be in the future!")
    private LocalDate releaseDate;

    @NotNull(message = "You must select a style!")
    @Enumerated(EnumType.STRING)
    private StyleEnum style;

}
