package api.fleetManagementAPI.repositories;

import api.fleetManagementAPI.models.Trajectory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TrajectoryRepository extends JpaRepository<Trajectory, Integer> {
    @Query(nativeQuery = true,
            value = "select * from trajectories where taxi_id=:taxi_id")
    Page<Trajectory> findByTaxiIdAndDate(Integer taxi_id, Pageable page);

    //las fechas están guardadas como timeStamp, que incluye hasta la hora, pero el endpoint solo nos pide fecha (convertir timeStamp a String para comparar)
}
