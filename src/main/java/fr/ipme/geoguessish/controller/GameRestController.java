package fr.ipme.geoguessish.controller;

import com.fasterxml.jackson.annotation.JsonView;
import fr.ipme.geoguessish.dto.GameCreateDTO;
import fr.ipme.geoguessish.entity.Game;
import fr.ipme.geoguessish.json_views.JsonViews;
import fr.ipme.geoguessish.service.GameService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/game")
public class GameRestController {

    private GameService gameService;

    @PostMapping
    @JsonView(JsonViews.GameShow.class)
    public Game create(@Valid @RequestBody GameCreateDTO dto, Principal principal) {
        return gameService.create(dto, principal);
    }

    @GetMapping
    @JsonView(JsonViews.GameList.class)
    public List<Game> list() {
        return gameService.findAll();
    }

    @GetMapping("/{id}")
    @JsonView(JsonViews.GameShow.class)
    public Game show(@PathVariable String id) {
        return gameService.findOneById(id);
    }

}
