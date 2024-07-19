package api.fleetManagementAPI.services;
import api.fleetManagementAPI.models.Taxi;
import api.fleetManagementAPI.repositories.TaxiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListTaxiService {
    @Autowired
    private TaxiRepository taxiRepository;

    public List<Taxi> runList() {
        return taxiRepository.findAll();
    }

}

//dentro de listTaxiService debo conectar con repositories

