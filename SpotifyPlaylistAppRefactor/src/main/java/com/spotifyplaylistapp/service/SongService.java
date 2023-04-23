package com.spotifyplaylistapp.service;

import com.spotifyplaylistapp.model.binding.SongsByGenreBindingModel;

import com.spotifyplaylistapp.model.service.SongServiceModel;

import java.util.Set;

public interface SongService {

    void addSong(SongServiceModel songServiceModel);

    SongServiceModel findByTitle(String title);

    Set<SongServiceModel> getPlaylist(Long id);

    void addSongToPlaylist(Long id);
     SongsByGenreBindingModel getSongs();

     String getTotalDuration(Long id);


    void removeSongFromPlaylist(Long id);
}
