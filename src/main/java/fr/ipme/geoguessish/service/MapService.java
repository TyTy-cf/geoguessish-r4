package fr.ipme.geoguessish.service;

import fr.ipme.geoguessish.dto.MapDTO;
import fr.ipme.geoguessish.entity.Map;
import fr.ipme.geoguessish.repository.MapRepository;
import fr.ipme.geoguessish.service.interfaces.MapServiceInterface;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class MapService implements MapServiceInterface<Map, MapDTO, MapDTO, Long> {

    private MapRepository mapRepository;

    @Override
    public Map create(MapDTO item) {
        Map map = new Map();
        map.setName(item.getName());
        map.setCreatedAt(LocalDateTime.now());
        return mapRepository.saveAndFlush(map);
    }

    @Override
    public Map findOneById(Long id) {
        return mapRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<Map> findAll() {
        return mapRepository.findAll();
    }

    @Override
    public Map update(MapDTO item, Long id) {
        Map map = findOneById(id);
        map.setName(item.getName());
        return mapRepository.saveAndFlush(map);
    }

}
