package fr.miage.lroux.station.repository;

import fr.miage.lroux.station.entity.Station;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepoStation extends CrudRepository<Station, Long> {
}
