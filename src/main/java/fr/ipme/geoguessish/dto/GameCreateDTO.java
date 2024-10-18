package fr.ipme.geoguessish.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GameCreateDTO {

    @NotNull
    private Boolean hasPan;

    @NotNull
    private Boolean hasZoom;

    @NotNull
    private Boolean hasMove;

    @NotBlank
    private Integer maximumTime;

    @NotBlank
    private Integer nbRound;

    @NotBlank
    private Long mapId;

}
