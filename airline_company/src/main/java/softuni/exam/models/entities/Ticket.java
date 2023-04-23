package softuni.exam.models.entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tickets")
public class Ticket extends BaseEntity{
    @Size(min = 2)
    @Column(unique = true,nullable = false)
    private String serialNumber;

    @Positive
    @Column(nullable = false)
    private double price;

    @Column(name = "take_off", nullable = false)
    private LocalDateTime takeoff;

    @ManyToOne(optional = false)
    @JoinColumn(name = "passenger_id")
    private Passenger passenger;

    @ManyToOne(optional = false)
    @JoinColumn(name = "from_town_id")
    private Town fromTown;

    @ManyToOne(optional = false)
    @JoinColumn(name = "to_town_id")
    private Town toTown;

    @ManyToOne(optional = false)
    @JoinColumn(name = "plane_id")
    private Plane plane;

    @Override
    public String toString() {
        return fromTown.getName() + " - " + toTown.getName();
    }
}
