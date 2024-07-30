package api.fleetManagementAPI.controllers;


//AQUÍ DESARROLLAREMOS LOS 2 ENDPOINTS REQUERIDOS

import api.fleetManagementAPI.services.MailService;
import api.fleetManagementAPI.models.Trajectory;
import api.fleetManagementAPI.services.ListLatestTrajectoriesService;
import api.fleetManagementAPI.services.ListTrajectoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/trajectories")
public class TrajectoryController {
    @Autowired // aquí debemos instanciar todos los servicios a los q debemos conectar para ejecutar las peticiones a construir.
    private ListTrajectoryService listTrajectoryService;
    @Autowired
    private ListLatestTrajectoriesService listLatestTrajectoriesService;
    @Autowired
    private MailService mailService;

    @GetMapping()
    public List<Trajectory> getTrajectory(@RequestParam Integer taxiId,
                                          @RequestParam String date,
                                          @RequestParam(defaultValue="0") Integer page,
                                          @RequestParam(defaultValue="10") Integer limit)
    {
        return listTrajectoryService
                .runList(taxiId, date, page, limit);
    }

    @GetMapping("/latest")
    public List<Trajectory> getLatestTrajectories(@RequestParam(required = false, defaultValue="0") Integer page,
                                                  @RequestParam(required = false, defaultValue="10") Integer limit)
    {
        return listLatestTrajectoriesService
                .runList(page, limit);
    }

    @GetMapping("/export")
    public ResponseEntity<Object> mailService(@RequestParam Integer taxiId,
                                              @RequestParam String date,
                                              @RequestParam String email,
                                              @RequestParam(required = false, defaultValue="0") Integer page,
                                              @RequestParam(required = false, defaultValue="10") Integer limit) throws IOException {
        ResponseEntity<Object> exportTrajectories =
                mailService.sendMessageTo(email,
                        "Prueba mail FMA",
                        "Hola, esto es un mail de prueba enviado automáticamente al hacer una petición a FMA",
                        taxiId, date, page, limit);
        return exportTrajectories;
    }
}
