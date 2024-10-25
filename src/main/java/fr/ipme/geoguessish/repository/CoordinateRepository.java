package fr.ipme.geoguessish.repository;

import fr.ipme.geoguessish.entity.Coordinate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CoordinateRepository extends JpaRepository<Coordinate, Long> {

    Optional<Coordinate> findByLatitudeAndLongitude(String latitude, String longitude);

}