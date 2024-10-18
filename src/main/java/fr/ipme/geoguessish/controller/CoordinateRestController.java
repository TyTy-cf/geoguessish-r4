package fr.ipme.geoguessish.controller;

import fr.ipme.geoguessish.dto.CoordinateCreateDTO;
import fr.ipme.geoguessish.entity.Coordinate;
import fr.ipme.geoguessish.service.CoordinateService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/coordinate")
public class CoordinateRestController {

    private CoordinateService coordinateService;

    @PostMapping
    public Coordinate create(@Valid @RequestBody CoordinateCreateDTO dto) {
        return coordinateService.create(dto);
    }

}
