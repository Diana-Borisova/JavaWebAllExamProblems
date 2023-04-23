package com.example.modelmapper;

import com.example.modelmapper.dtos.*;
import com.example.modelmapper.entities.Game;
import com.example.modelmapper.entities.User;
import com.example.modelmapper.services.GameService;
import com.example.modelmapper.services.OrderService;
import com.example.modelmapper.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

@Component
public class ConsoleRunner implements CommandLineRunner {
    final private UserService userService;
    final private GameService gameService;
   final private OrderService orderService;

    @Autowired
    public ConsoleRunner(UserService userService, GameService gameService, OrderService orderService) {
        this.userService = userService;
        this.gameService = gameService;
        this.orderService = orderService;
    }

    @Override
    public void run(String... args) {
        Scanner scan = new Scanner(System.in);

        String[] input;
        while ((input = scan.nextLine().split("\\|")).length > 0) {
            String command = input[0];

            if (command.equals("")) {
                return;
            }

            switch (command) {
                case "RegisterUser":
                    String email = input[1];
                    String password = input[2];
                    String confirmPassword = input[3];
                    String fullName = input[4];
                    UserRegisterDto userRegisterDto = new UserRegisterDto(email, password, confirmPassword, fullName);
                    System.out.println(this.userService.registerUser(userRegisterDto));
                    break;
                case "LoginUser":
                    email = input[1];
                    password = input[2];
                    UserLoginDto userLoginInDto = new UserLoginDto(email, password);
                    userService.logIn(userLoginInDto);
                    break;
                case "Logout":
                    userLoginInDto = new UserLoginDto();
                    userService.logOut(userLoginInDto);
                    break;

                case "AddGame":
                    String title = input[1];
                    BigDecimal price = BigDecimal.valueOf(Double.parseDouble(input[2]));
                    float size = Float.parseFloat(input[3]);
                    String trailer = input[4];
                    String thumbnailURL = input[5];
                    String description = input[6];

                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                    LocalDate releaseDate = LocalDate.parse(input[7], formatter);

                    GameDto gameDto = new GameDto(title,trailer, thumbnailURL, size, price, description, releaseDate);
                    System.out.println(gameService.addGame(gameDto));
                    break;
                case "EditGame":
                    long id = Long.parseLong(input[1]);
                    String[ ] commandForPrice = input[2].split("=");
                    price = BigDecimal.valueOf(Double.parseDouble(commandForPrice[1]));
                    String[ ] commandForSize = input[3].split("=");
                    size = Float.parseFloat(commandForSize[1]);

                    GameDto gameDto1 = new GameDto(price, size);
                    gameService.update(id, gameDto1);
                    break;
                case "DeleteGame":
                    id = Long.parseLong(input[1]);

                    System.out.println(gameService.deleteById(id));
                    break;
                case"AllGames":
                    final StringBuilder sb = new StringBuilder();

                    gameService.getAllGamesTitleAndPrice()
                            .forEach(g -> sb.append(String.format("%s %.2f%n", g.getTitle(), g.getPrice())));

                    System.out.println(sb.toString().trim());
                    break;
                case"DetailGame":
                   final StringBuilder sb1 = new StringBuilder();
                    gameService.getGameDetailsViewDtoByTitle()
                            .forEach(g->sb1.append(String.format("Title: %s%n", g.getTitle()))
                                    .append(String.format("Price: %.2f%n", g.getPrice()))
                                    .append(String.format("Description: %s%n",g.getDescription()))
                                    .append(String.format("Release date: %s%n", g.getReleaseDate()
                                            .format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))))
                                    .append(String.format("Size: %.2f%n", g.getSize()))
                                    .append(String.format("Thumbnail URL: %s%n", g.getImageThumbnail()))
                                    .append(String.format("Youtube trailer ID: %s%n", g.getTrailerIdent())));
                    System.out.println(sb1.toString().trim());
                    break;
                case"OwnedGames":
                   userService.getGamesByUser().forEach(System.out::println);
                    break;
                default:
                    System.out.println("No command found");
                    break;
            }
        }
    }
}

