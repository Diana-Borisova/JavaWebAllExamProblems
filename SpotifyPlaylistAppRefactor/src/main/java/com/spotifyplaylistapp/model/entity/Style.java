package com.spotifyplaylistapp.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "styles")
public class Style extends BaseEntity{
    @Column(nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    private StyleEnum name;

    @Column(nullable = true, columnDefinition = "TEXT")
    private String description;

    @OneToMany(mappedBy = "style", fetch = FetchType.LAZY)
    private Set<Song> songs;
}
