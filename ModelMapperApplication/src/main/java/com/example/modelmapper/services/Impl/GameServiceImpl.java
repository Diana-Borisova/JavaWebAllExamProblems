package com.example.modelmapper.services.Impl;

import com.example.modelmapper.dtos.GameDto;
import com.example.modelmapper.dtos.GameTitleAndPriceViewDto;
import com.example.modelmapper.dtos.GameTitleViewDto;
import com.example.modelmapper.dtos.UserLoginDto;
import com.example.modelmapper.entities.Game;
import com.example.modelmapper.entities.User;
import com.example.modelmapper.repositories.GameRepository;
import com.example.modelmapper.services.GameService;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class GameServiceImpl implements GameService {
    final private GameRepository gameRepository;

    @Autowired
    public GameServiceImpl(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Override
    public String findByTitle(String title) {
        if (gameRepository.findByTitle(title) == null) {
            return "No such game";
        }
        return this.gameRepository.findByTitle(title);
    }

    @Override
    public String addGame(GameDto gameDto) {
        ModelMapper mapper = new ModelMapper();

        var validationMsg = validate(gameDto);
        if (validationMsg != null) {
            return validationMsg;
        }

        Game game = mapper.map(gameDto, Game.class);
        gameRepository.save(game);
        return String.format("Added %s", game.getTitle());

    }

    @Override
    public void update(long id, GameDto gameDto) {
        ModelMapper mapper = new ModelMapper();

        mapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT);
        Game game = mapper.map(gameDto, Game.class);

        game.setId(id);
        Game gameById = gameRepository.findGameById(id);
        gameById.setPrice(gameDto.getPrice());
        gameById.setSize(gameDto.getSize());
        this.gameRepository.save(gameById);
        System.out.printf("Edited %s%n", gameById.getTitle());

    }

    @Override
    public String deleteById(long id) {
        Game game = gameRepository.findGameById(id);
        if (game == null) {
            return "Game not found.";
        }
        gameRepository.deleteById(id);
        return String.format("Deleted " + game.getTitle());
    }

    @Override
    public GameTitleViewDto getGameTitleViewDtoById(Long id) {
        ModelMapper mapper = new ModelMapper();

        Optional<Game> game = this.gameRepository.findById(id);
        return game.map(game1 -> mapper.map(game1, GameTitleViewDto.class)).orElse(null);
    }


    @Override
    public Set<GameTitleAndPriceViewDto> getAllGamesTitleAndPrice() {
        ModelMapper mapper = new ModelMapper();
        System.out.println(gameRepository.findAll());
        gameRepository.findAll().forEach(System.out::println);
        return this.gameRepository.findAll().stream()
                .map(game -> mapper.map(game, GameTitleAndPriceViewDto.class))
                .collect(Collectors.toSet());

    }

    @Override
    public Set<GameDto> getGameDetailsViewDtoByTitle() {
        ModelMapper mapper = new ModelMapper();

        return gameRepository.findAll().stream()
                .map(game -> mapper.map(game, GameDto.class))
                .collect(Collectors.toSet());
    }

    @Override
    public Game findGameById(long id) {
        return gameRepository.findGameById(id);
    }

    private String validate(GameDto gameDto) {
        if (gameDto.getTitle().length() < 3 && gameDto.getTitle().length() > 100) {
            return "Game title should be more than 3 and less than 100 chars.";
        }
        if (gameDto.getPrice().compareTo(new BigDecimal("0.00")) == 0) {
            return "Price must be positive.";
        }
        if (gameDto.getSize() < 0) {
            return "Size must be positive.";
        }
        if (gameDto.getTrailerIdent().length() != 11) {
            return "Trailer id should be equal to 11 chars.";
        }
        if (!gameDto.getImageThumbnail().contains("http://") && !gameDto.getImageThumbnail().contains("https://")) {
            return "Thumbnail should contains http://, https:// ";
        }
        if (gameDto.getDescription().length() < 20) {
            return "Description must be 20 chars.";
        }
        return null;
    }
}
