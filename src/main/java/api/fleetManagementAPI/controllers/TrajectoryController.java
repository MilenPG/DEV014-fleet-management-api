package api.fleetManagementAPI.controllers;


//AQUÍ DESARROLLAREMOS LOS 2 ENDPOINTS REQUERIDOS

import api.fleetManagementAPI.models.Trajectory;
import api.fleetManagementAPI.services.ListTrajectoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/trajectories")
public class TrajectoryController {
    @Autowired
    private ListTrajectoryService listTrajectoryService;

    @GetMapping()
    public List<Trajectory> getTrajectory(@RequestParam Integer taxiId,
                                          @RequestParam String date,
                                          @RequestParam(defaultValue="0") Integer page,
                                          @RequestParam(defaultValue="10") Integer limit)
    {
        return listTrajectoryService
                .runList(taxiId, date, page, limit);
    }
}
