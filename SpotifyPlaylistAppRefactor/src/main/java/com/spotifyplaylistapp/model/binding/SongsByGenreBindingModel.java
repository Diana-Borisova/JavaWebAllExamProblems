package com.spotifyplaylistapp.model.binding;

import com.spotifyplaylistapp.model.service.SongServiceModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SongsByGenreBindingModel {

    private Set<SongServiceModel> popSongs;
    private Set<SongServiceModel> rockSongs;
    private Set<SongServiceModel> jazzSongs;

}
