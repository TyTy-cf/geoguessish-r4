package fr.ipme.geoguessish.controller;

import com.fasterxml.jackson.annotation.JsonView;
import fr.ipme.geoguessish.dto.RoundCreateDTO;
import fr.ipme.geoguessish.entity.Round;
import fr.ipme.geoguessish.json_views.JsonViews;
import fr.ipme.geoguessish.service.RoundService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/round")
public class RoundRestController {

    private RoundService roundService;

    @PostMapping
    @JsonView(JsonViews.RoundShow.class)
    public Round create(@Valid @RequestBody RoundCreateDTO dto) {
        return roundService.create(dto);
    }

}
