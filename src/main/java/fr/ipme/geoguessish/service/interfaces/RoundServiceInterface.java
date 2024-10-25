package fr.ipme.geoguessish.service.interfaces;

import fr.ipme.geoguessish.dto.RoundCreateDTO;
import fr.ipme.geoguessish.dto.RoundUpdateDTO;
import fr.ipme.geoguessish.entity.Round;

public interface RoundServiceInterface extends CreateServiceInterface<Round, RoundCreateDTO> {

    Round update(RoundUpdateDTO item, Long id);

}
