package fr.miage.lroux.station.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
//import fr.miage.lroux.gestionVoiture.Voiture;

/**
 * Entity representing a Station.
 * This class is used to store information about a station, including its ID, number of places,
 * number of places taken, localisation, and number of free places.
 */
@Entity
public class Station {

    /**
     * Station ID.
     * This is the primary key for the Station entity.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long stationId;

    /**
     * Number of places in the station.
     */
    private int nbPlaces;

    /**
     * Number of places taken in the station.
     */
    private int nbPlacesTaken;

    /**
     * Localisation of the station.
     * This is stored as a list of doubles, representing coordinates (e.g., latitude and longitude).
     */
    @ElementCollection
    private List<Double> localisation;

    /**
     * Number of free places in the station.
     */
    private int nbPlacesFree;

    /**
     * Default constructor for JPA.
     * This constructor is required for JPA to create instances of the Station entity.
     */
    public Station() {}

    /**
     * Constructor for Station without stationId.
     *
     * @param localisation   List of doubles representing the station's coordinates.
     * @param nbPlaces       Total number of places in the station.
     * @param nbPlacesTaken  Number of places currently taken.
     * @param nbPlacesFree   Number of free places in the station.
     */
    public Station(List<Double> localisation, int nbPlaces, int nbPlacesTaken, int nbPlacesFree) {
        this.localisation = localisation;
        this.nbPlaces = nbPlaces;
        this.nbPlacesTaken = nbPlacesTaken;
        this.nbPlacesFree = nbPlacesFree;
    }

    /**
     * Constructor for Station with stationId.
     *
     * @param stationId      Unique identifier for the station.
     * @param nbPlaces       Total number of places in the station.
     * @param nbPlacesTaken  Number of places currently taken.
     * @param localisation   List of doubles representing the station's coordinates.
     * @param nbPlacesFree   Number of free places in the station.
     */
    public Station(long stationId, int nbPlaces, int nbPlacesTaken, List<Double> localisation, int nbPlacesFree) {
        this.stationId = stationId;
        this.nbPlaces = nbPlaces;
        this.nbPlacesTaken = nbPlacesTaken;
        this.localisation = localisation;
        this.nbPlacesFree = nbPlacesFree;
    }

    /**
     * Get the station ID.
     *
     * @return the unique identifier for the station.
     */
    public long getStationId() {
        return stationId;
    }

    /**
     * Set the station ID.
     *
     * @param stationId the unique identifier for the station.
     */
    public void setStationId(long stationId) {
        this.stationId = stationId;
    }

    /**
     * Get the number of places taken in the station.
     *
     * @return the number of places currently occupied.
     */
    public int getNbPlacesTaken() {
        return nbPlacesTaken;
    }

    /**
     * Set the number of places taken in the station.
     *
     * @param nbPlacesTaken the number of places currently occupied.
     */
    public void setNbPlacesTaken(int nbPlacesTaken) {
        this.nbPlacesTaken = nbPlacesTaken;
    }

    /**
     * Get the localisation of the station.
     *
     * @return a list of doubles representing the station's coordinates.
     */
    public int getNbPlaces() {
        return nbPlaces;
    }

    /**
     * Set the number of places in the station.
     *
     * @param nbPlaces the total number of places in the station.
     */
    public void setNbPlaces(int nbPlaces) {
        this.nbPlaces = nbPlaces;
    }

    /**
     * Get the number of free places in the station.
     *
     * @return the number of places currently available.
     */
    public int getNbPlacesFree() {
        return nbPlacesFree;
    }

    /**
     * Set the number of free places in the station.
     *
     * @param nbPlacesFree the number of places currently available.
     */
    public void setNbPlacesFree(int nbPlacesFree) {
        this.nbPlacesFree = nbPlacesFree;
    }

    /**
     * Get the localisation of the station.
     *
     * @return a list of doubles representing the station's coordinates.
     */
    public List<Double> getLocalisation() {
        return localisation;
    }

    /**
     * Set the localisation of the station.
     *
     * @param localisation a list of doubles representing the station's coordinates.
     */
    public void setLocalisation(List<Double> localisation) {
        this.localisation = localisation;
    }
}
