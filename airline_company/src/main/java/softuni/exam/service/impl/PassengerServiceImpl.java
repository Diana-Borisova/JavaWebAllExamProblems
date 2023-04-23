package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.entities.Passenger;
import softuni.exam.models.entities.Town;
import softuni.exam.models.entities.dto.PassengerImportDto;
import softuni.exam.repository.PassengerRepository;
import softuni.exam.repository.TownRepository;
import softuni.exam.service.PassengerService;
import softuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import static softuni.exam.constants.Messages.*;
import static softuni.exam.constants.Paths.PASSENGERS_PATH;

@Service
public class PassengerServiceImpl implements PassengerService {
    private final PassengerRepository passengerRepository;
    private final ModelMapper modelMapper;
    private final Gson gson;
    private final ValidationUtil validationUtils;;

    private final TownRepository townRepository;

    @Autowired
    public PassengerServiceImpl(PassengerRepository passengerRepository, ModelMapper modelMapper, Gson gson, ValidationUtil validationUtils, TownRepository townRepository) {
        this.passengerRepository = passengerRepository;
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.validationUtils = validationUtils;
        this.townRepository = townRepository;
    }

    @Override
    public boolean areImported() {
        return this.passengerRepository.count()>0;
    }

    @Override
    public String readPassengersFileContent() throws IOException {
        return Files.readString(Path.of(PASSENGERS_PATH));
    }

    @Override
    public String importPassengers() throws IOException {
        StringBuilder sb = new StringBuilder();
        Arrays.stream(gson
                        .fromJson(readPassengersFileContent(), PassengerImportDto[].class))
                .filter(passengerImportDto -> {
                    boolean isValid = this.validationUtils.isValid(passengerImportDto);
                    Optional<Town> town = this.townRepository.findTownByName(passengerImportDto.getTown());
                    if (!town.isPresent()) {
                        isValid = false;
                    }

                    sb.append(isValid
                                    ? String.format(VALID_PASSENGER,
                                    passengerImportDto.getLastName(),
                                    passengerImportDto.getEmail())
                                    : INVALID_PASSENGER)
                            .append(System.lineSeparator());
                    ;
                    return isValid;
                })
                .map(passengerImportDto -> {
                    Passenger passenger = modelMapper.map(passengerImportDto, Passenger.class);
                    Town town = this.townRepository.findByName(passengerImportDto.getTown());
                    passenger.setTown(town);

                    return passenger;
                })
                .forEach(this.passengerRepository::save);

        return sb.toString();
    }
        @Override
        public String getPassengersOrderByTicketsCountDescendingThenByEmail () {
            return this.passengerRepository
                    .findAllByOrderByTicketsDescEmailAsc()
                    .orElseThrow(NoSuchElementException::new)
                    .stream()
                    .map(Passenger::toString)
                    .collect(Collectors.joining(System.lineSeparator()));
        }

    @Override
    public Optional<Passenger> getPassengerByEmail(String email) {
        return this.passengerRepository.getPassengerByEmail(email);
    }
}
