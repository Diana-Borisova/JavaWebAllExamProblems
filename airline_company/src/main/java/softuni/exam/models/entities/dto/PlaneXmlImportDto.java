package softuni.exam.models.entities.dto;

import lombok.*;

import javax.persistence.Column;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "plane")
@XmlAccessorType(XmlAccessType.FIELD)
public class PlaneXmlImportDto {

    @NonNull
    @XmlElement(name = "register-number")
    @Size(min = 5)
    private String registerNumber;

    @Positive
    @NonNull
    @XmlElement
    private int capacity;

    @Size(min = 2)
    @NonNull
    @XmlElement
    private String airline;
}
