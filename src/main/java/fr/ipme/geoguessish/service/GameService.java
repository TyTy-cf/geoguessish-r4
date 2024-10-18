package fr.ipme.geoguessish.service;

import fr.ipme.geoguessish.dto.GameCreateDTO;
import fr.ipme.geoguessish.entity.Game;
import fr.ipme.geoguessish.repository.GameRepository;
import fr.ipme.geoguessish.service.interfaces.GameServiceInterface;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class GameService implements GameServiceInterface<Game, GameCreateDTO, String> {

    private GameRepository gameRepository;
    private MapService mapService;
    private UserService userService;

    @Override
    public Game create(GameCreateDTO item, Principal principal) {
        Game game = new Game();
        game.setCreatedAt(LocalDateTime.now());
        game.setHasPan(item.getHasPan());
        game.setHasMove(item.getHasMove());
        game.setHasZoom(item.getHasZoom());
        game.setNbRounds(item.getNbRound());
        game.setMaximumTime(item.getMaximumTime());
        game.setMap(mapService.findOneById(item.getMapId()));
        game.setUser(userService.findByEmail(principal.getName()));
        return gameRepository.saveAndFlush(game);
    }

    @Override
    public Game findOneById(String id) {
        return gameRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<Game> findAll() {
        return gameRepository.findAll();
    }

}
