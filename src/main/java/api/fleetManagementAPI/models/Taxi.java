package api.fleetManagementAPI.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity(name="taxis") // aquí se contextualiza lo que estamos referenciando, en este caso la tabla taxis de la BD.
public class Taxi {

    @Id
    private Integer id;
    private String plate;


    public Integer getId() {
        return id;
    }
    public String getPlate() {
        return plate;
    }


    public void setPlate(String plate) {
        this.plate = plate;
    }
    public void setId(Integer id) {
        this.id = id;
    }
}

/*
Modelar taxis
* 1. Darle atributos: id, placa (con sus contructores)
* 2. Cuando logre listar todos los taxis, agregar paginación.
*/