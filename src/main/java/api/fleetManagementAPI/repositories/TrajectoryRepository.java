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
    Page<Trajectory> findByTaxiIdAndDate(Integer taxi_id, String date, Pageable page);

    @Query(nativeQuery = true,
            value = "SELECT ID, TAXI_ID, date, LONGITUDE, LATITUDE " +

                    "FROM (" + "SELECT ID, TAXI_ID, date, LONGITUDE, LATITUDE, " +
                    "ROW_NUMBER() OVER (PARTITION BY TAXI_ID ORDER BY date DESC) as row_num " + // partition by ~ group by
                    "FROM public.TRAJECTORIES) " +

                    "AS ranked " +
                    "WHERE row_num = 1") // producto de la partición ordenada descendentemente, la columna 1 sería el grupo requerido
    List<Trajectory> findByLatestTrajectories(Pageable page);

    //las fechas están guardadas como timeStamp, que incluye hasta la hora, pero el endpoint solo nos pide fecha (convertir timeStamp a String para comparar)
    /*- El método a crear en TrajectoryRepository, debe convertir el tipo de dato timeStamp a String
	- ?? Esto para que pueda aplicarse Contains y así retorne todos aquellos elementos que contengan
	los caracteres pasados por parámetro ??
	(ej: para 2 trayectorias con distinto horario pero misma fecha 12-10-2024830, 12-10-2024T14:45,
	si busco 12-10-2024, sabrá retornar ambas trayectorias, pues basta que ambas contengan la porción de String dada.) */
}
