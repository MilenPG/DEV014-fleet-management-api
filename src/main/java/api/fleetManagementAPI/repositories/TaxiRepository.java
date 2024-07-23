package api.fleetManagementAPI.repositories;


import api.fleetManagementAPI.models.Taxi;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface TaxiRepository extends JpaRepository<Taxi,Integer> {

Page<Taxi> findByPlateContains(String plate, Pageable page); // método para poder pasar parametro plate. Solo agregando "Contains" al final, funciona, ya q es una palabra reservada de JPA para filtrar por contenido
}

//Extender interfaz que ya tiene JPA por default