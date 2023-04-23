package com.spotifyplaylistapp.service.impl;

import com.spotifyplaylistapp.model.binding.SongAddBindingModel;
import com.spotifyplaylistapp.model.binding.SongsByGenreBindingModel;
import com.spotifyplaylistapp.model.entity.Song;
import com.spotifyplaylistapp.model.entity.Style;
import com.spotifyplaylistapp.model.entity.StyleEnum;
import com.spotifyplaylistapp.model.entity.User;
import com.spotifyplaylistapp.model.service.SongServiceModel;
import com.spotifyplaylistapp.repository.SongRepository;
import com.spotifyplaylistapp.service.SongService;
import com.spotifyplaylistapp.service.StyleService;
import com.spotifyplaylistapp.service.UserService;
import com.spotifyplaylistapp.util.LoggedUser;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@Service
public class SongServiceImpl implements SongService {
    private final SongRepository songRepository;
    private final UserService userService;
    private final StyleService styleService;
    private final ModelMapper modelMapper;
    private final LoggedUser loggedUser;

    public SongServiceImpl(SongRepository songRepository, UserService userService, StyleService styleService, ModelMapper modelMapper, LoggedUser loggedUser) {
        this.songRepository = songRepository;
        this.userService = userService;
        this.styleService = styleService;
        this.modelMapper = modelMapper;
        this.loggedUser = loggedUser;
    }

    @Override
    public void addSong(SongServiceModel songServiceModel) {

        this.songRepository.save(this.mapSong(songServiceModel));

     /*  Song song =  modelMapper.map(songServiceModel, Song.class);
       song.setReleaseDate(songServiceModel.getReleaseDate());
       song.setTitle(songServiceModel.getTitle());
       song.setDuration(songServiceModel.getDuration());
       song.setStyle(this.styleService.findByName(songServiceModel.getStyle()));
       song.setPerformer(songServiceModel.getPerformer());
       this.songRepository.save(song);*/

    }

    @Override
    public SongServiceModel findByTitle(String title) {
        return this.songRepository
                .findByTitle(title)
                .map(this::mapSongServiceModelToSong)
                .orElse(null);

        /*return this.songRepository
                .findByTitle(title)
                .map(song -> modelMapper.map(song, SongServiceModel.class))
                .orElse(null);*/
    }

    @Override
    public Set<SongServiceModel> getPlaylist(Long id) {
        return this.songRepository.findAllByUserId(id)
                .stream()
                .map(this::mapSongServiceModelToSong)
                        .collect(Collectors.toSet());

    }

    @Override
    public void addSongToPlaylist(Long id) {
        Song song = this.songRepository.findById(id).orElse(null);
        if (song != null){
            User user = this.userService.findById(loggedUser.getId());
                Set<Song> playlist = this.songRepository.findAllByUserId(loggedUser.getId());
                playlist.add(song);
                userService.addSongToUser(loggedUser.getId(), song);
                userService.save(user);

        }

    }
    public SongsByGenreBindingModel getSongs() {
        SongsByGenreBindingModel songs = new SongsByGenreBindingModel();
        songs.setPopSongs(getSongsByGenre((this.styleService.findByName(StyleEnum.POP))));
        songs.setJazzSongs(getSongsByGenre((this.styleService.findByName(StyleEnum.JAZZ))));
        songs.setRockSongs(getSongsByGenre((this.styleService.findByName(StyleEnum.ROCK))));
        return songs;
    }

    @Override
    public String getTotalDuration(Long id) {
        Set<Song> playlist = this.songRepository.findAllByUserId(id);

        int timeInSec = playlist
                .stream()
                .map(Song::getDuration)
                .reduce(Integer::sum).orElse(0);

        int seconds = timeInSec % 60;
        Integer minutes = (timeInSec / 60) % 60;

        String totalTime = "0";
        if (timeInSec > 0) {
            totalTime = String.format(minutes + ":" + seconds);
        }
        return totalTime;
    }

    @Override
    public void removeSongFromPlaylist(Long id) {
        User user = userService.findById(id);
        user.getPlaylist().clear();
        this.userService.save(user);

    }

    private Set<SongServiceModel> getSongsByGenre(Style style) {
        return this.songRepository.findByStyle_Name(style.getName())
                .stream()
                .map(song -> modelMapper.map(song, SongServiceModel.class))
                .collect(Collectors.toSet());
    }
    private Song mapSong(SongServiceModel songServiceModel) {
        Song song = new Song();
        Style style = this.styleService.findByName(songServiceModel.getStyle());

        song.setDuration(songServiceModel.getDuration());
        song.setPerformer(songServiceModel.getPerformer());
        song.setStyle(style);
        song.setTitle(songServiceModel.getTitle());
        song.setReleaseDate(songServiceModel.getReleaseDate());
        return song;
    }

    private SongServiceModel mapSongServiceModelToSong(Song song) {
        SongServiceModel songServiceModel = new SongServiceModel();
        songServiceModel.setId(song.getId());
        songServiceModel.setDuration(song.getDuration());
        songServiceModel.setPerformer(song.getPerformer());
        songServiceModel.setTitle(song.getTitle());
        return songServiceModel;
    }

}
