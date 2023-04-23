package com.spotifyplaylistapp.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.validation.constraints.*;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "songs")
public class Song extends BaseEntity{

    @Column(nullable = false)
    @Size(min= 3, max = 20, message = "Performer name length must be between 3 and 20 characters")
    private String performer;

    @Column(nullable = false)
    @Size(min= 2, max = 20, message = "Performer name length must be between 3 and 20 characters")
    private String title;

    @Column(nullable = false)
    @Positive
    private int duration;

    @Column(nullable = true)
    @PastOrPresent
    private LocalDate releaseDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "style_id")
    private Style style;

    @ManyToMany(mappedBy = "playlist")
    private Set<User> users;



}
