package softuni.exam.models.entities.dto;

import lombok.*;
import softuni.exam.models.entities.Passenger;
import softuni.exam.models.entities.Plane;
import softuni.exam.models.entities.Town;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDateTime;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "ticket")
@XmlAccessorType(XmlAccessType.FIELD)
public class TicketXmlImportDto {
    @Size(min = 2)
    @NonNull
    @XmlElement(name = "serial-number")
    private String serialNumber;

    @Positive
    @NonNull
    @XmlElement
    private double price;

    @NonNull
    @XmlElement(name = "take-off")
    private String takeoff;

    @NonNull
    @XmlElement(name = "from-town")
    private FromTownXMLDto fromTown;

    @NonNull
    @XmlElement(name = "to-town")
    private ToTownXMLDto toTown;

    @NonNull
    @XmlElement(name = "passenger")
    private PassengerEmailXMLDto passenger;

    @NonNull
    @XmlElement(name= "plane")
    private PlaneRegisterNumberXMLDto plane;
}
