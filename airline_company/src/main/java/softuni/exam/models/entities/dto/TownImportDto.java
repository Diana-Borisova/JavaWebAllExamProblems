package softuni.exam.models.entities.dto;

import com.google.gson.annotations.Expose;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class TownImportDto {
    @Expose
    @Size(min = 2)
    @NotNull
    private String name;

    @Positive
    @NotNull
    @Expose
    private int population;


    @NotNull
    @Expose
    private String guide;
}
