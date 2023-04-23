package softuni.exam.models.entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "passengers")
public class Passenger extends BaseEntity{
    @Size(min = 2)
    @Column(nullable = false)
    private String firstName;

    @Size(min = 2)
    @Column(nullable = false)
    private String lastName;

    @Positive
    @Column(nullable = false)
    private int age;

    @Column(nullable = false)
    private String phoneNumber;

    @Email
    @Column(unique = true,nullable = false)
    private String email;

    @ManyToOne(optional = false)
    @JoinColumn(name = "town_id")
    private Town town;
    @OneToMany(fetch = FetchType.EAGER,targetEntity = Ticket.class, mappedBy = "passenger")
    private List<Ticket> tickets;
    public Passenger() {
        this.tickets = new ArrayList<>();
    }
    @Override
    public String toString() {


        return String.format("Passenger %s %s", firstName, lastName) + System.lineSeparator() +
                String.format(" Email - %s", email) + System.lineSeparator() +
                String.format("Phone - %s", phoneNumber) + System.lineSeparator() +
                String.format(" Number of tickets - %d", tickets.size());
    }
}
