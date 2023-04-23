package com.spotifyplaylistapp.model.view;

import com.spotifyplaylistapp.model.entity.Song;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class SongViewModel {

    private Long id;

    private String performer;

    private String title;

    private int duration;
    private Set<Song> playlist;


}
