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

    public List<Taxi> runList(String plate, Integer pageNumber, Integer pageSize) {
        //Sort plate = Sort.by(queryParam);
        Pageable page = PageRequest.of(pageNumber, pageSize);

        System.out.println(plate);
        return taxiRepository.findByPlateContains(plate, page).getContent();
    }

}

//dentro de listTaxiService debo conectar con repositories

