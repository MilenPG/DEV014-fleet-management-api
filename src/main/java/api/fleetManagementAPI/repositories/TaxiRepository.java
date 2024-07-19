package api.fleetManagementAPI.repositories;


import api.fleetManagementAPI.models.Taxi;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaxiRepository extends JpaRepository<Taxi,Integer> {
}

//Extender interfaz que ya tiene JPA por default