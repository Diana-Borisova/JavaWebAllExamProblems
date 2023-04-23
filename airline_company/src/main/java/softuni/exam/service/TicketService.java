package softuni.exam.service;


import softuni.exam.models.entities.Ticket;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.Optional;

//ToDo - Before start App implement this Service and set areImported to return false
public interface TicketService {

    boolean areImported();

    String readTicketsFileContent() throws IOException;
	
	String importTickets() throws JAXBException;
    Optional<Ticket> findBySerialNumber(String string);
}
