package softuni.exam.models.entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "planes")
public class Plane extends BaseEntity {
    @Size(min = 5)
    @Column(unique = true,nullable = false)
    private String registerNumber;

    @Positive
    @Column(nullable = false)
    private int capacity;

    @Size(min = 2)
    @Column(nullable = false)
    private String airline;
}
