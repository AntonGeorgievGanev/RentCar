package bg.rentacar.model.entity;

import bg.rentacar.model.enums.RentOrderStatus;
import jakarta.persistence.*;

import java.sql.Time;
import java.time.LocalDate;

@Entity
@Table(name = "rents")
public class Rent extends BaseEntity {
    @Column(nullable = false)
    private String location;
    @Column(nullable = false)
    private LocalDate pickUpDate;
    @Column(nullable = false)
    private LocalDate dropOffDate;
    @Column(nullable = false)
    private Time pickUpTime;
    @Column(nullable = false)
    private Time dropOffTime;
    @Column(nullable = false)
    private String returnLocation;

    @ManyToOne
    private Extra extra;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private RentOrderStatus status;
    @OneToOne(optional = false)
    private Car car;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDate getPickUpDate() {
        return pickUpDate;
    }

    public void setPickUpDate(LocalDate pickUpDate) {
        this.pickUpDate = pickUpDate;
    }

    public LocalDate getDropOffDate() {
        return dropOffDate;
    }

    public void setDropOffDate(LocalDate dropOffDate) {
        this.dropOffDate = dropOffDate;
    }

    public Time getPickUpTime() {
        return pickUpTime;
    }

    public void setPickUpTime(Time pickUpTime) {
        this.pickUpTime = pickUpTime;
    }

    public Time getDropOffTime() {
        return dropOffTime;
    }

    public void setDropOffTime(Time dropOffTime) {
        this.dropOffTime = dropOffTime;
    }

    public String getReturnLocation() {
        return returnLocation;
    }

    public void setReturnLocation(String returnLocation) {
        this.returnLocation = returnLocation;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public RentOrderStatus getStatus() {
        return status;
    }

    public void setStatus(RentOrderStatus status) {
        this.status = status;
    }

    public Extra getExtra() {
        return extra;
    }

    public void setExtra(Extra extra) {
        this.extra = extra;
    }
}
