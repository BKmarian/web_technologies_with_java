package lab9example1.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Movie {

    private int id;

    @NotBlank(message = "The name of the movie cannot be null")
    private String name;

    @NotBlank(message = "The type of the movie cannot be null")
    private String type;

    @NotNull
    private Date date;

    @NotNull
    @Min(1)
    @Max(10)
    private float grade;
}
