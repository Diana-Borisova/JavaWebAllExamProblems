package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.entities.Passenger;
import softuni.exam.models.entities.Plane;
import softuni.exam.models.entities.Ticket;
import softuni.exam.models.entities.Town;
import softuni.exam.models.entities.dto.TicketWrapperDto;
import softuni.exam.repository.PassengerRepository;
import softuni.exam.repository.PlaneRepository;
import softuni.exam.repository.TicketRepository;
import softuni.exam.repository.TownRepository;
import softuni.exam.service.TicketService;
import softuni.exam.util.LocalDateAdapter;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

import static softuni.exam.constants.Messages.*;
import static softuni.exam.constants.Paths.TICKETS_PATH;

@Service
public class TicketServiceImpl implements TicketService {
    private final TicketRepository ticketRepository;
    private final ModelMapper modelMapper;
    private final XmlParser xmlParser;
    private final ValidationUtil validationUtil;

    private final TownRepository townRepository;

    private final PlaneRepository planeRepository;

    private final PassengerRepository passengerRepository;

    private final LocalDateAdapter localDateAdapter;

    @Autowired
    public TicketServiceImpl(TicketRepository ticketRepository, ModelMapper modelMapper, XmlParser xmlParser, ValidationUtil validationUtil, TownRepository townRepository, PlaneRepository planeRepository, PassengerRepository passengerRepository, LocalDateAdapter localDateAdapter) {
        this.ticketRepository = ticketRepository;
        this.modelMapper = modelMapper;
        this.xmlParser = xmlParser;
        this.validationUtil = validationUtil;
        this.townRepository = townRepository;
        this.planeRepository = planeRepository;
        this.passengerRepository = passengerRepository;
        this.localDateAdapter = localDateAdapter;
    }

    @Override
    public boolean areImported() {
        return this.ticketRepository.count()>0;
    }

    @Override
    public String readTicketsFileContent() throws IOException {
        return Files.readString(Path.of(TICKETS_PATH));
    }

    @Override
    public String importTickets() throws JAXBException {
        StringBuilder sb = new StringBuilder();

        xmlParser
                .fromFile(TICKETS_PATH, TicketWrapperDto.class)
                .getTicketXmlImportDto()
                .stream()
                .filter(ticketXmlImportDto -> {
                    boolean isValid = validationUtil.isValid(ticketXmlImportDto);
                    Optional<Ticket> ticketExist = this.ticketRepository.findBySerialNumber(ticketXmlImportDto.getSerialNumber());

                    Optional<Town> townFrom = this.townRepository.findTownByName(ticketXmlImportDto.getFromTown().getName());
                    Optional<Town> townTo = this.townRepository.findTownByName(ticketXmlImportDto.getFromTown().getName());

                    Optional<Passenger> passenger = this.passengerRepository.getPassengerByEmail(ticketXmlImportDto.getPassenger().getEmail());


                    Optional<Plane> plane = this.planeRepository.findPlaneByRegisterNumber(ticketXmlImportDto.getPlane().getRegisterNumber());

                    if (ticketExist.isPresent() || townFrom.isEmpty() ||townTo.isEmpty() || passenger.isEmpty()||plane.isEmpty()) {
                        isValid = false;
                    }
                    sb
                            .append(isValid
                                    ? INVALID_TICKET
                                    :String.format(VALID_TICKET,
                            ticketXmlImportDto.getFromTown().getName(),
                            ticketXmlImportDto.getToTown().getName()) )
                            .append(System.lineSeparator());

                    return isValid;
                })
                .map(ticketXmlImportDto -> {
                    Ticket ticket= modelMapper.map(ticketXmlImportDto, Ticket.class);
                    Town townFrom = this.townRepository.findTownByName(ticketXmlImportDto.getFromTown().getName()).orElseThrow();
                    Town townTo = this.townRepository.findTownByName(ticketXmlImportDto.getToTown().getName()).orElseThrow();
                    Passenger passenger = this.passengerRepository.getPassengerByEmail(ticketXmlImportDto.getPassenger().getEmail()).orElseThrow();
                    Plane plane = this.planeRepository.findPlaneByRegisterNumber(ticketXmlImportDto.getPlane().getRegisterNumber()).orElseThrow();
                  ticket.setSerialNumber(ticketXmlImportDto.getSerialNumber());
                    ticket.setPrice(ticketXmlImportDto.getPrice());
                    try {
                        ticket.setTakeoff(localDateAdapter.unmarshal(ticketXmlImportDto.getTakeoff()));
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    ticket.setFromTown(townFrom);
                    ticket.setToTown(townTo);
                    ticket.setPassenger(passenger);
                    ticket.setPlane(plane);

                    return ticket;
                })
                .forEach(this.ticketRepository::save);

        return sb.toString();
    }

    @Override
    public Optional<Ticket> findBySerialNumber(String string) {
        return this.ticketRepository.findBySerialNumber(string);
    }

    public Ticket findTicketBySerialNumber(String serialNumber){
        return this.ticketRepository.findTicketBySerialNumber(serialNumber);
    }
}
