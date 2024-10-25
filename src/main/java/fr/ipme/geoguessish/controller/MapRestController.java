package fr.ipme.geoguessish.controller;

import com.fasterxml.jackson.annotation.JsonView;
import fr.ipme.geoguessish.dto.MapDTO;
import fr.ipme.geoguessish.entity.Map;
import fr.ipme.geoguessish.json_views.JsonViews;
import fr.ipme.geoguessish.service.MapService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/map")
public class MapRestController {

    private MapService mapService;

    @PostMapping
    @JsonView(JsonViews.MapShow.class)
    public Map create(@Valid @RequestBody MapDTO dto) {
        return mapService.create(dto);
    }

    @PutMapping("/{id}")
    @JsonView(JsonViews.MapShow.class)
    public Map create(@Valid @RequestBody MapDTO dto, @PathVariable Long id) {
        return mapService.update(dto, id);
    }

    @GetMapping
    @JsonView(JsonViews.MapList.class)
    public List<Map> list() {
        return mapService.findAll();
    }

    @GetMapping("/{id}")
    @JsonView(JsonViews.MapShow.class)
    public Map show(@PathVariable Long id) {
        return mapService.findOneById(id);
    }

}
