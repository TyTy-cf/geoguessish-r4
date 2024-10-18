package fr.ipme.geoguessish.service;

import fr.ipme.geoguessish.dto.RoundCreateDTO;
import fr.ipme.geoguessish.entity.Round;
import fr.ipme.geoguessish.repository.RoundRepository;
import fr.ipme.geoguessish.service.interfaces.CreateServiceInterface;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RoundService implements CreateServiceInterface<Round, RoundCreateDTO> {

    private RoundRepository roundRepository;

    @Override
    public Round create(RoundCreateDTO item) {
        Round round = new Round();
        return roundRepository.saveAndFlush(round);
    }

}
