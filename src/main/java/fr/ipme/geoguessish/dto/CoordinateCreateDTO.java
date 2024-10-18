package fr.ipme.geoguessish.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CoordinateCreateDTO {

    @NotBlank
    private String latitude;

    @NotBlank
    private String longitude;

}
