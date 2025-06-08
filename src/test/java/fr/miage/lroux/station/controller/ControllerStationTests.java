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
        station = new Station("51,46", 80, 42,38);
        station = repoStation.save(station);
    }

    @Test
    public void getStationByid() throws Exception {
        mvc.perform(get("/api/station/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.position", is(station.getPosition())))
                .andExpect(jsonPath("$.nbPlaces", is(station.getNbPlaces())))
                .andExpect(jsonPath("$.nbPlacesTaken", is(station.getNbPlacesTaken())))
                .andExpect(jsonPath("$.nbPlacesFree", is(station.getNbPlacesFree())));
    }

    @Test
    public void createStation() throws Exception{
        Station stationObject = new Station("50,68", 80, 40,40);
        ObjectMapper om = new ObjectMapper();
        String stationJson = om.writeValueAsString(stationObject);
        mvc.perform(post("/api/station/create")
                        .contentType("application/json")
                        .content(stationJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.position", is(stationObject.getPosition())))
                .andExpect(jsonPath("$.nbPlaces", is(stationObject.getNbPlaces())))
                .andExpect(jsonPath("$.nbPlacesTaken", is(stationObject.getNbPlacesTaken())))
                .andExpect(jsonPath("$.nbPlacesFree", is(stationObject.getNbPlacesFree())));
    }

    @Test
    public void deleteStationByid() throws Exception {
        mvc.perform(delete("/api/station/1"))
                .andExpect(status().isOk());
    }

}
