package fr.ipme.geoguessish.service;

import fr.ipme.geoguessish.dto.RoundCreateDTO;
import fr.ipme.geoguessish.entity.Coordinate;
import fr.ipme.geoguessish.entity.Round;
import fr.ipme.geoguessish.repository.CoordinateRepository;
import fr.ipme.geoguessish.repository.RoundRepository;
import fr.ipme.geoguessish.service.interfaces.CreateServiceInterface;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Service
@AllArgsConstructor
public class RoundService implements CreateServiceInterface<Round, RoundCreateDTO> {

    private RoundRepository roundRepository;
    private CoordinateRepository coordinateRepository;
    private GameService gameService;

    @Override
    public Round create(RoundCreateDTO item) {
        Round round = new Round();
        round.setGame(gameService.findOneById(item.getGameId()));
        round.setCreatedAt(LocalDateTime.now());
        // Generate a random coordinate to initialize the round
        Random random = new Random();
        List<Coordinate> coordinates = coordinateRepository.findAll();
        Coordinate coordinate = coordinates.get(random.nextInt(0, (coordinates.size() - 1)));
        round.setOrigin(coordinate);
        return roundRepository.saveAndFlush(round);
    }

}
