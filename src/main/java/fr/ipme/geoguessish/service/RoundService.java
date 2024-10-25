package fr.ipme.geoguessish.service;

import fr.ipme.geoguessish.dto.RoundCreateDTO;
import fr.ipme.geoguessish.dto.RoundUpdateDTO;
import fr.ipme.geoguessish.entity.Coordinate;
import fr.ipme.geoguessish.entity.Round;
import fr.ipme.geoguessish.repository.CoordinateRepository;
import fr.ipme.geoguessish.repository.RoundRepository;
import fr.ipme.geoguessish.service.interfaces.RoundServiceInterface;
import fr.ipme.geoguessish.service.utils.CoordinateUtils;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class RoundService implements RoundServiceInterface {

    private RoundRepository roundRepository;
    private CoordinateRepository coordinateRepository;
    private GameService gameService;
    private CoordinateUtils coordinateUtils;

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

    @Override
    public Round update(RoundUpdateDTO item, Long id) {
        Round round = roundRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        round.setTime(item.getTime());
        Optional<Coordinate> optional = coordinateRepository.
                findByLatitudeAndLongitude(item.getLatitude(), item.getLongitude());

        Coordinate coordinate;
        if (optional.isEmpty()) {
            coordinate = new Coordinate();
            coordinate.setLongitude(item.getLongitude());
            coordinate.setLatitude(item.getLatitude());
            coordinateRepository.saveAndFlush(coordinate);
        } else {
            coordinate = optional.get();
        }
        round.setSelected(coordinate);

        NumberFormat nf = NumberFormat.getInstance(Locale.FRANCE);
        try {
            round.setDistance(Math.round(coordinateUtils.meters(
                    nf.parse(round.getOrigin().getLatitude()).doubleValue(),
                    nf.parse(round.getOrigin().getLongitude()).doubleValue(),
                    nf.parse(round.getSelected().getLatitude()).doubleValue(),
                    nf.parse(round.getSelected().getLongitude()).doubleValue()
            )));
        } catch (ParseException e) {
            return null;
        }

        if(round.getDistance() > 10000000) {
            round.setPoints(0);
        } else {
            round.setPoints(coordinateUtils.points(5000, round.getDistance(), 10000000L));
        }

        return roundRepository.saveAndFlush(round);
    }
}
