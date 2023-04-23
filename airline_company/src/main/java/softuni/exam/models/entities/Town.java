package softuni.exam.models.entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "towns")
public class Town extends BaseEntity {

    @Size(min = 2)
    @Column(unique = true, nullable = false)
    private String name;

    @Positive
    @Column( nullable = false)
    private int population;


    @Column(columnDefinition = "TEXT", nullable = false)
    private String guide;

}
