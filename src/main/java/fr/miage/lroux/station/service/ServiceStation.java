package fr.miage.lroux.station.service;

import fr.miage.lroux.station.entity.Station;
import fr.miage.lroux.station.repository.RepoStation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ServiceStation {

    @Autowired
    private RepoStation repoStation;

    public Station createStation(Station station) throws Exception {
        Optional<Station> stationOptional = repoStation.findById(station.getStationId());
        if (stationOptional.isPresent()) {
            throw new Exception("A station with this ID " + station.getStationId() + " already exists");
        }
        repoStation.save(station);
        return station;
    }

    public Station getStationById(Long stationId) throws Exception {
        Optional<Station> stationOptional = repoStation.findById(stationId);
        if (stationOptional.isEmpty()) {
            throw new Exception("A station with this ID " + stationId + " doesn't exists");
        }
        return stationOptional.get();
    }

    public void deleteStation(Long stationId) throws Exception {
        Optional<Station> stationOptional = repoStation.findById(stationId);
        if (stationOptional.isEmpty()) {
            throw new Exception("A station with this ID " + stationId + " does not exist");
        }
        repoStation.deleteById(stationId);
    }
}
