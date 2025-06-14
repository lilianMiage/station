package fr.miage.lroux.station.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.miage.lroux.station.entity.Station;
import fr.miage.lroux.station.repository.RepoStation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application-integrationtest.properties")
public class ControllerStationTests {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private RepoStation repoStation;

    private Station station;

    @BeforeEach
    public void setUp(){
        station = new Station(List.of(50.68, 5.55), 80, 40, 40);
        station = repoStation.save(station);
    }

    @Test
    public void getStationByid() throws Exception {
        mvc.perform(get("/api/station/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.localisation", is(station.getLocalisation())))
                .andExpect(jsonPath("$.nbPlaces", is(station.getNbPlaces())))
                .andExpect(jsonPath("$.nbPlacesTaken", is(station.getNbPlacesTaken())))
                .andExpect(jsonPath("$.nbPlacesFree", is(station.getNbPlacesFree())));
    }

    @Test
    public void createStation() throws Exception{
        Station stationObject = new Station(List.of(50.68, 5.55), 80, 40,40);
        ObjectMapper om = new ObjectMapper();
        String stationJson = om.writeValueAsString(stationObject);
        mvc.perform(post("/api/station/create")
                        .contentType("application/json")
                        .content(stationJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.localisation", is(stationObject.getLocalisation())))
                .andExpect(jsonPath("$.nbPlaces", is(stationObject.getNbPlaces())))
                .andExpect(jsonPath("$.nbPlacesTaken", is(stationObject.getNbPlacesTaken())))
                .andExpect(jsonPath("$.nbPlacesFree", is(stationObject.getNbPlacesFree())));
    }

    @Test
    public void deleteStationByid() throws Exception {
        mvc.perform(delete("/api/station/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void updateStationWhenAddingCar_ShouldDecreaseFreePlacesAndIncreaseTaken() throws Exception {
        int placesFreeBefore = station.getNbPlacesFree();
        int placesTakenBefore = station.getNbPlacesTaken();

        mvc.perform(put("/api/station/add/" + station.getStationId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nbPlacesFree").value(placesFreeBefore - 1))
                .andExpect(jsonPath("$.nbPlacesTaken").value(placesTakenBefore + 1));
    }

    @Test
    public void updateStationWhenRetrievingCar_ShouldIncreaseFreePlacesAndDecreaseTaken() throws Exception {
        int placesFreeBefore = station.getNbPlacesFree();
        int placesTakenBefore = station.getNbPlacesTaken();

        mvc.perform(put("/api/station/retrieve/" + station.getStationId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nbPlacesFree").value(placesFreeBefore + 1))
                .andExpect(jsonPath("$.nbPlacesTaken").value(placesTakenBefore - 1));
    }

}
