package api.fleetManagementAPI.controllers;

import api.fleetManagementAPI.models.Taxi;
import api.fleetManagementAPI.services.ListTaxiService;
import org.hibernate.annotations.Filter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/taxis") // Base para que todas las URI de este conjunto de endpoints empiecen con el mismo prefijo.
public class TaxiController {
    @Autowired // lo usamos para instanciar la clase y conectar las capas
    private ListTaxiService listTaxiService;

    @GetMapping()
    public List<Taxi> getTaxi(@RequestParam(defaultValue = "") String plate,
                              @RequestParam(defaultValue = "0") Integer page,
                              @RequestParam(defaultValue = "10") Integer limit)
                               {
        return listTaxiService
                .runList(plate, page, limit);
    }
}