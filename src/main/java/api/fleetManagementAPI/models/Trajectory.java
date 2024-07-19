package api.fleetManagementAPI.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import org.springframework.data.annotation.Id;
import java.util.Date;

@Entity(name="trajectories")
public class Trajectory {
    @Id
    private Integer id;
    private Double latitude;
    private Double longitude;
    private Date date;
    @JoinColumn(name = "taxi_id")
    //con esto, estamos comunicandole a la aplicación que el tipo de dato Taxi lo encuentra en la columna "taxi_id que es de la otra tabla "taxies"
    private Taxi taxi;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Taxi getTaxi() {
        return taxi;
    }

    public void setTaxi(Taxi taxi) {
        this.taxi = taxi;
    }

}