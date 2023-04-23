package softuni.exam.service;


import softuni.exam.models.entities.Plane;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.Optional;

//ToDo - Before start App implement this Service and set areImported to return false
public interface PlaneService {

    boolean areImported();

    String readPlanesFileContent() throws IOException, JAXBException;
	
	String importPlanes() throws JAXBException, IOException;
    Optional<Plane> findPlaneByRegisterNumber(String registerNumber);
}
