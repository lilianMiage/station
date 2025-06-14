package fr.miage.lroux.station.controller;


import fr.miage.lroux.station.entity.Station;
import fr.miage.lroux.station.service.ServiceStation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for managing station operations.
 * Provides endpoints to create, retrieve, update, and delete stations.
 */
@RestController
@RequestMapping("/api/station")
public class ControllerStation {

    /**
     * Service for station operations.
     * This service handles the business logic for station management.
     */
    @Autowired
    private ServiceStation serviceStation;

    /**
     * Creates a new station.
     * @param station The station to be created.
     * @return The created station.
     * @throws Exception If an error occurs during station creation.
     */
    @PostMapping("/create")
    public Station createStation(@RequestBody Station station) throws Exception {
        return serviceStation.createStation(station);
    }

    /**
     * Retrieves a station by its ID.
     * @param id The ID of the station to retrieve.
     * @return The station with the specified ID.
     * @throws Exception If the station does not exist.
     */
    @GetMapping("/{id}")
    public Station getStationById(@PathVariable Long id) throws Exception {
        return serviceStation.getStationById(id);
    }

    /**
     * Deletes a station by its ID.
     * @param id The ID of the station to delete.
     * @throws Exception If the station does not exist.
     */
    @DeleteMapping("/{id}")
    public void deleteStation(@PathVariable Long id) throws Exception {
        serviceStation.deleteStation(id);
    }

    /**
     * Retrieves all stations.
     * @return A list of all stations.
     */
    @GetMapping("stations")
    public List<Station> getStations(){
        return serviceStation.getStations();
    }

    /**
     * Updates the number of available places in a station when a car is added.
     * @param stationId The ID of the station to update.
     * @throws Exception If an error occurs during the update.
     */
    @PutMapping("add/{stationId}")
    public Station updateStationWhenAddingCar(@PathVariable long stationId) throws Exception {
        return serviceStation.updatePlacesWhenAddingCar(stationId);
    }

    /**
     * Updates the number of available places in a station when a car is retrieved.
     * @param stationId The ID of the station to update.
     * @throws Exception If an error occurs during the update.
     */
    @PutMapping("retrieve/{stationId}")
    public Station updateAfterRetrievingCar(@PathVariable long stationId) throws Exception {
        return serviceStation.updatePlacesWhenRetrievingCar(stationId);
    }
}
