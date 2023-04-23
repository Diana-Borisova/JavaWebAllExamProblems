package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.entities.Plane;
import softuni.exam.models.entities.dto.PlaneImportWrapperDto;
import softuni.exam.repository.PlaneRepository;
import softuni.exam.service.PlaneService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

import static softuni.exam.constants.Messages.INVALID_PLANE;
import static softuni.exam.constants.Messages.VALID_PLANE;

import static softuni.exam.constants.Paths.PLANES_PATH;

@Service
public class PlaneServiceImpl implements PlaneService {
    private final PlaneRepository planeRepository;
    private final ModelMapper modelMapper;
    private final XmlParser xmlParser;
    private final ValidationUtil validationUtil;

    @Autowired
    public PlaneServiceImpl(PlaneRepository planeRepository, ModelMapper modelMapper, XmlParser xmlParser, ValidationUtil validationUtil) {
        this.planeRepository = planeRepository;
        this.modelMapper = modelMapper;
        this.xmlParser = xmlParser;
        this.validationUtil = validationUtil;
    }

    @Override
    public boolean areImported() {
        return this.planeRepository.count()>0;
    }

    @Override
    public String readPlanesFileContent() throws IOException {
        return Files.readString(Path.of(PLANES_PATH));
    }
    @Override
    public String importPlanes() throws JAXBException, IOException {
            StringBuilder sb = new StringBuilder();

            xmlParser
                    .fromFile(PLANES_PATH, PlaneImportWrapperDto.class)
                    .getPlaneXmlImportDto()
                    .stream()
                    .filter(planeXmlImportDto -> {
                        boolean isValid = validationUtil.isValid(planeXmlImportDto);


                        sb
                                .append(isValid
                                        ? String.format(VALID_PLANE,
                                        planeXmlImportDto.getRegisterNumber())
                                        : INVALID_PLANE)
                                .append(System.lineSeparator());

                        return isValid;
                    })
                    .map(planeXmlImportDto -> {
                        Plane plane = modelMapper.map(planeXmlImportDto, Plane.class);

                        return plane;
                    })
                    .forEach(this.planeRepository::save);

            return sb.toString();
        }

    @Override
    public Optional<Plane> findPlaneByRegisterNumber(String registerNumber) {
        return this.planeRepository.findPlaneByRegisterNumber(registerNumber);
    }
}
