package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.entities.Town;
import softuni.exam.models.entities.dto.TownImportDto;
import softuni.exam.repository.TownRepository;
import softuni.exam.service.TownService;
import softuni.exam.util.ValidationUtil;

import javax.validation.Validator;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Optional;


import static softuni.exam.constants.Messages.INVALID_TOWN;
import static softuni.exam.constants.Messages.VALID_TOWN;
import static softuni.exam.constants.Paths.TOWNS_PATH;

@Service
public class TownServiceImpl implements TownService {
    private final TownRepository townRepository;
    private final ModelMapper modelMapper;
    private final Gson gson;
    private final ValidationUtil validationUtils;;

    @Autowired
    public TownServiceImpl(TownRepository townRepository, ModelMapper modelMapper, Gson gson,  ValidationUtil validationUtils) {
        this.townRepository = townRepository;
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.validationUtils = validationUtils;

    }

    @Override
    public boolean areImported()  {
        return this.townRepository.count()>0;
    }

    @Override
    public String readTownsFileContent() throws IOException {
        return Files.readString(Path.of(TOWNS_PATH));
    }

    @Override
    public String importTowns() throws IOException {
        StringBuilder sb = new StringBuilder();
        Arrays.stream(gson
                        .fromJson(readTownsFileContent(), TownImportDto[].class))
                .filter(townImportDto -> {
                    boolean isValid = this.validationUtils.isValid(townImportDto);
                   /* Optional<Town> townByName = this.townRepository.findCityByCityName(townImportDto.getName());
                    if (town.isPresent()) {
                        isValid = false;
                    }*/
                    sb.append(isValid
                                    ? String.format(VALID_TOWN,
                                    townImportDto.getName(),
                                    townImportDto.getPopulation())
                                    : INVALID_TOWN)
                            .append(System.lineSeparator());
                    return isValid;
                })
                .map(townImportDto -> {
                    Town town = modelMapper.map(townImportDto, Town.class);

                    return town;
                })
                .forEach(this.townRepository::save);

        return sb.toString();
    }

    @Override
    public Town findByName(String townName) {
        return this.townRepository.findByName(townName);
    }
}
