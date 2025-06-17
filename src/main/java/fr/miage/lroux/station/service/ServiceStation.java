package fr.miage.lroux.station.service;

import fr.miage.lroux.station.entity.Station;
import fr.miage.lroux.station.repository.RepoStation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service for managing station operations.
 * Provides methods to create, retrieve, update, and delete stations.
 */
@Service
public class ServiceStation {

    /**
     * Repository for station operations.
     * This repository provides CRUD operations for Station entities.
     */
    @Autowired
    private RepoStation repoStation;

    /**
     * Creates a new station.
     * @param station The station to be created.
     * @return The created station.
     * @throws Exception If a station with the same ID already exists.
     */
    public Station createStation(Station station) throws Exception {
        Optional<Station> stationOptional = repoStation.findById(station.getStationId());
        if (stationOptional.isPresent()) {
            throw new Exception("A station with this ID " + station.getStationId() + " already exists");
        }
        if (station.getNbPlacesFree() != station.getNbPlaces() - station.getNbPlacesTaken()) {
            throw new Exception("The number of free places does not match the total places minus taken places");
        }
        repoStation.save(station);
        return station;
    }

    /**
     * Retrieves a station by its ID.
     * @param stationId The ID of the station to retrieve.
     * @return The station with the specified ID.
     * @throws Exception If the station does not exist.
     */
    public Station getStationById(Long stationId) throws Exception {
        Optional<Station> stationOptional = repoStation.findById(stationId);
        if (stationOptional.isEmpty()) {
            throw new Exception("A station with this ID " + stationId + " doesn't exists");
        }
        return stationOptional.get();
    }

    /**
     * Deletes a station by its ID.
     * @param stationId The ID of the station to delete.
     * @throws Exception If the station does not exist.
     */
    public void deleteStation(Long stationId) throws Exception {
        Optional<Station> stationOptional = repoStation.findById(stationId);
        if (stationOptional.isEmpty()) {
            throw new Exception("A station with this ID " + stationId + " does not exist");
        }
        repoStation.deleteById(stationId);
    }

    /**
     * Retrieves all stations.
     * @return A list of all stations.
     */
    public List<Station> getStations(){
        return (List<Station>) repoStation.findAll();
    }

    /**
     * Updates the number of free and taken places in a station when a car is added.
     * @param stationId The ID of the station to update.
     * @throws Exception If there are no free places in the station.
     */
    public Station updatePlacesWhenAddingCar(long stationId) throws Exception {
        Station station = getStationById(stationId);
        if (station.getNbPlacesFree() == 0){
            throw new Exception("No free places in this stations");
        }
        station.setNbPlacesFree(station.getNbPlacesFree()-1);
        station.setNbPlacesTaken(station.getNbPlacesTaken()+1);
        repoStation.save(station);
        return station;
    }

    /**
     * Updates the number of free and taken places in a station when a car is retrieved.
     * @param stationId The ID of the station to update.
     * @throws Exception If the station does not exist.
     */
    public Station updatePlacesWhenRetrievingCar(long stationId) throws Exception {
        Station station = getStationById(stationId);
        station.setNbPlacesFree(station.getNbPlacesFree()+1);
        station.setNbPlacesTaken(station.getNbPlacesTaken()-1);
        repoStation.save(station);
        return station;
    }
}
