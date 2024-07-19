package api.fleetManagementAPI.controllers;

import api.fleetManagementAPI.models.Taxi;
import api.fleetManagementAPI.services.ListTaxiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/taxis") // Base para que todas las URI de este conjunto de endpoints empiecen con el mismo prefijo.
public class TaxiController {
    @Autowired // lo usamos para instanciar la clase y conectar las capas
    private ListTaxiService listTaxiService;

    @GetMapping()
    public List<Taxi> getTaxi() {
        return listTaxiService.runList(); //llamo al service y ejecuto su método
    }
}
