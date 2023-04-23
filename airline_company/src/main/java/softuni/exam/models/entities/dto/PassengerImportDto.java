package softuni.exam.models.entities.dto;

import com.google.gson.annotations.Expose;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import softuni.exam.models.entities.Town;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class PassengerImportDto {
    @Expose
    @NotNull
    @Size(min = 2)
    private String firstName;

    @Expose
    @NotNull
    @Size(min = 2)
    private String lastName;

    @Positive
    @Expose
    @NotNull
    private int age;

    @Expose
    @NotNull
    private String phoneNumber;

    @Email
    @Expose
    @NotNull
    private String email;

    @Expose
    @NotNull
    private String town;
}
