package com.spotifyplaylistapp.service.impl;

import com.spotifyplaylistapp.model.binding.SongAddBindingModel;
import com.spotifyplaylistapp.model.binding.SongsByGenreBindingModel;
import com.spotifyplaylistapp.model.entity.Song;
import com.spotifyplaylistapp.model.entity.Style;
import com.spotifyplaylistapp.model.entity.StyleEnum;
import com.spotifyplaylistapp.model.entity.User;
import com.spotifyplaylistapp.model.service.SongServiceModel;
import com.spotifyplaylistapp.model.view.SongViewModel;
import com.spotifyplaylistapp.repository.SongRepository;
import com.spotifyplaylistapp.repository.UserRepository;
import com.spotifyplaylistapp.service.SongService;
import com.spotifyplaylistapp.service.StyleService;
import com.spotifyplaylistapp.service.UserService;
import com.spotifyplaylistapp.util.LoggedUser;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.lang.management.PlatformLoggingMXBean;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@Service
public class SongServiceImpl implements SongService {
    private final SongRepository songRepository;

    private final UserRepository userRepository;
    private final UserService userService;
    private final StyleService styleService;
    private final ModelMapper modelMapper;
    private final LoggedUser loggedUser;

    public SongServiceImpl(SongRepository songRepository, UserRepository userRepository, UserService userService, StyleService styleService, ModelMapper modelMapper, LoggedUser loggedUser) {
        this.songRepository = songRepository;
        this.userRepository = userRepository;
        this.userService = userService;
        this.styleService = styleService;
        this.modelMapper = modelMapper;
        this.loggedUser = loggedUser;
    }

    @Override
    public void addSong(SongServiceModel songServiceModel) {

       Song song =  modelMapper.map(songServiceModel, Song.class);
       song.setReleaseDate(songServiceModel.getReleaseDate());
       song.setTitle(songServiceModel.getTitle());
       song.setDuration(songServiceModel.getDuration());
       song.setStyle(this.styleService.findByName(songServiceModel.getStyle()));
       song.setPerformer(songServiceModel.getPerformer());
       this.songRepository.save(song);
      //  this.userService.addSongToUser(loggedUser.getId(), song);
    }

    @Override
    public SongServiceModel findByTitle(String title) {
        return this.songRepository
                .findByTitle(title)
                .map(song -> modelMapper.map(song, SongServiceModel.class))
                .orElse(null);
    }

    @Override
    public List<SongViewModel> findAllByStyle_Name(StyleEnum styleEnum) {
        return this.songRepository.findAllByStyle_Name(styleEnum)
                .stream()
                .map(song -> modelMapper.map(song, SongViewModel.class))
                .collect(Collectors.toList());

    }

    @Override
    public Set<SongServiceModel> getPlaylist(Long id) {
        return this.songRepository.findAllByUserId(id)
                .stream()
                .map(song -> modelMapper.map(song, SongServiceModel.class))
                        .collect(Collectors.toSet());

    }

    @Override
    public Set<SongServiceModel> findByStyle_Name(StyleEnum styleEnum) {
        return this.songRepository.findByStyle_Name(styleEnum).stream()
                .map(song -> modelMapper.map(song, SongServiceModel.class))
                .collect(Collectors.toSet());
    }

    @Override
    public void addSongToPlaylist(Long id) {
        Song song = this.songRepository.findById(id).orElse(null);
        if (song != null){
            User user = this.userRepository.findById(loggedUser.getId()).orElse(null);
                Set<Song> playlist = this.songRepository.findAllByUserId(loggedUser.getId());
                playlist.add(song);
                userService.addSongToUser(loggedUser.getId(), song);
                userRepository.save(user);

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
        User user = userRepository.findById(id).orElse(null);
        user.getPlaylist().clear();
        this.userRepository.save(user);

    }

    private Set<SongServiceModel> getSongsByGenre(Style style) {
        return this.songRepository.findByStyle_Name(style.getName())
                .stream()
                .map(song -> modelMapper.map(song, SongServiceModel.class))
                .collect(Collectors.toSet());
    }
    private Song mapSong(SongAddBindingModel songAddBindingModel) {
        Song song = new Song();
        Style style = this.styleService.findByName(songAddBindingModel.getStyle());

        song.setDuration(songAddBindingModel.getDuration());
        song.setPerformer(songAddBindingModel.getPerformer());
        song.setStyle(style);
        song.setTitle(songAddBindingModel.getTitle());
        song.setReleaseDate(songAddBindingModel.getReleaseDate());
        return song;
    }

}
