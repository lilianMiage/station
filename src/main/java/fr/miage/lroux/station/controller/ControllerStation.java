package fr.miage.lroux.station.controller;


import fr.miage.lroux.station.entity.Station;
import fr.miage.lroux.station.service.ServiceStation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/station")
public class ControllerStation {

    @Autowired
    private ServiceStation serviceStation;

    @PostMapping("create")
    public Station createStation(@RequestBody Station station) throws Exception {
        return serviceStation.createStation(station);
    }

    @GetMapping("/{id}")
    public Station getStationById(@PathVariable Long id) throws Exception {
        return serviceStation.getStationById(id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteStation(@PathVariable Long id) throws Exception {
        serviceStation.deleteStation(id);
    }

}
