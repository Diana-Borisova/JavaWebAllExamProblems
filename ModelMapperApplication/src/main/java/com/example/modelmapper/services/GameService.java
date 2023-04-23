package com.example.modelmapper.services;

import com.example.modelmapper.dtos.GameDto;
import com.example.modelmapper.dtos.GameTitleAndPriceViewDto;
import com.example.modelmapper.dtos.GameTitleViewDto;
import com.example.modelmapper.entities.Game;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface GameService {
    String addGame(GameDto gameDto);
    Game findGameById(long id);
    void update(long id, GameDto gameDto);
    Set<GameTitleAndPriceViewDto> getAllGamesTitleAndPrice();
    Set<GameDto> getGameDetailsViewDtoByTitle();
    String deleteById(long id);
    public GameTitleViewDto getGameTitleViewDtoById(Long id);
    String findByTitle(String title);





}
