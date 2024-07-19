package api.fleetManagementAPI.services;
import api.fleetManagementAPI.models.Taxi;
import api.fleetManagementAPI.repositories.TaxiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ListTaxiService {
    @Autowired
    private TaxiRepository taxiRepository;

    public List<Taxi> runList(Integer pageNumber, Integer pageSize) {
        Pageable page = PageRequest.of(pageNumber, pageSize);
        return taxiRepository.findAll(page).getContent();
    }

}

//dentro de listTaxiService debo conectar con repositories

