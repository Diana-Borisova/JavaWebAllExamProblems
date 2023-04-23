package com.spotifyplaylistapp.service;


import com.spotifyplaylistapp.model.binding.SongAddBindingModel;
import com.spotifyplaylistapp.model.binding.SongsByGenreBindingModel;
import com.spotifyplaylistapp.model.entity.Song;
import com.spotifyplaylistapp.model.entity.StyleEnum;
import com.spotifyplaylistapp.model.service.SongServiceModel;
import com.spotifyplaylistapp.model.view.SongViewModel;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface SongService {

    void addSong(SongServiceModel songServiceModel);

    SongServiceModel findByTitle(String title);

    List<SongViewModel> findAllByStyle_Name(StyleEnum styleEnum);

    Set<SongServiceModel> getPlaylist(Long id);

    Set<SongServiceModel> findByStyle_Name(StyleEnum styleEnum);
    void addSongToPlaylist(Long id);
     SongsByGenreBindingModel getSongs();

     String getTotalDuration(Long id);


    void removeSongFromPlaylist(Long id);
}
