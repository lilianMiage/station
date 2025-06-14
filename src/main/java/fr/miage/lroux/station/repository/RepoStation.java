package fr.miage.lroux.station.repository;

import fr.miage.lroux.station.entity.Station;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for Station entity.
 * This interface extends CrudRepository to provide CRUD operations for Station entities.
 */
@Repository
public interface RepoStation extends CrudRepository<Station, Long> {
}
