package api.fleetManagementAPI.models;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name="trajectories")
public class Trajectory {

    @Id
    private Integer id;
    private Double latitude;
    private Double longitude;
    private Date date;
    @JoinColumn(name="taxi_id")
    @ManyToOne
    private Taxi taxi; // la relación de este attr con trajectories es "many to one", muchas trayectorias pueden asociarse a un solo taxi

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
