package api.fleetManagementAPI.services;

import api.fleetManagementAPI.models.Trajectory;
import api.fleetManagementAPI.repositories.TrajectoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListTrajectoryService {
    @Autowired
    private TrajectoryRepository trajectoryRepository;

    public List<Trajectory> runList(Integer taxiId, Integer pageNumber, Integer pageLimit) {
        Pageable page = PageRequest.of(pageNumber, pageLimit);

        return trajectoryRepository.findByTaxiIdAndDate(taxiId, page).getContent();
    }
}
