package fr.ipme.geoguessish.service;

import fr.ipme.geoguessish.dto.CoordinateCreateDTO;
import fr.ipme.geoguessish.entity.Coordinate;
import fr.ipme.geoguessish.repository.CoordinateRepository;
import fr.ipme.geoguessish.service.interfaces.CreateServiceInterface;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CoordinateService implements CreateServiceInterface<Coordinate, CoordinateCreateDTO> {

    private CoordinateRepository coordinateRepository;

    @Override
    public Coordinate create(CoordinateCreateDTO item) {
        Coordinate coordinate = new Coordinate();
        coordinate.setLatitude(item.getLatitude());
        coordinate.setLongitude(item.getLongitude());
        return coordinateRepository.saveAndFlush(coordinate);
    }

}
