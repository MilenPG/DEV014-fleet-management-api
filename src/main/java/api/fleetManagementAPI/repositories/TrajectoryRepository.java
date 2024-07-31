package api.fleetManagementAPI.repositories;

import api.fleetManagementAPI.models.Trajectory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TrajectoryRepository extends JpaRepository<Trajectory, Integer> {

    @Query(nativeQuery = true,
            value = "select * from trajectories where taxi_id=:taxi_id and TO_CHAR(date,'dd-MM-yyyy')=:date")
    List<Trajectory> findByTaxiIdAndDate(Integer taxi_id, String date, Pageable page);

    @Query(nativeQuery = true,
            value = "select * from trajectories where taxi_id=:taxi_id and TO_CHAR(date,'dd-MM-yyyy')=:date")
    List<Trajectory> findByTaxiIdAndDateExport(Integer taxi_id, String date);

    @Query(nativeQuery = true,
            value = "SELECT ID, TAXI_ID, date, LONGITUDE, LATITUDE " +

                    "FROM (" + "SELECT ID, TAXI_ID, date, LONGITUDE, LATITUDE, " +
                    "ROW_NUMBER() OVER (PARTITION BY TAXI_ID ORDER BY date DESC) as row_num " + // partition by ~ group by
                    "FROM public.TRAJECTORIES) " +

                    "AS ranked " +
                    "WHERE row_num = 1")   // producto de la partición ordenada descendentemente, la columna 1 sería el grupo requerido
    List<Trajectory> findByLatestTrajectories(Pageable page);
}