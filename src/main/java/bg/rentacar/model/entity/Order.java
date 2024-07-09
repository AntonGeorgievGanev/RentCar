package bg.rentacar.model.entity;

import bg.rentacar.model.enums.RentOrderStatus;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

@Entity
@Table(name = "orders")
public class Order extends BaseEntity {
    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private LocalDate pickUpDate;

    @Column(nullable = false)
    private LocalDate dropOffDate;

    @Column(nullable = false)
    private LocalTime pickUpTime;

    @Column(nullable = false)
    private LocalTime dropOffTime;

    @Column(nullable = false)
    private String returnLocation;

    @ManyToOne
    private Extra extra;

    @ManyToOne
    private User user;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private RentOrderStatus status;

    @OneToOne
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

    public LocalTime getPickUpTime() {
        return pickUpTime;
    }

    public void setPickUpTime(LocalTime pickUpTime) {
        this.pickUpTime = pickUpTime;
    }

    public LocalTime getDropOffTime() {
        return dropOffTime;
    }

    public void setDropOffTime(LocalTime dropOffTime) {
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public BigDecimal getTotalPrice(){
        long days = ChronoUnit.DAYS.between(pickUpDate, dropOffDate);
        BigDecimal extraPrice = extra.getPrice();
        BigDecimal totalPrice = car.getPricePerDay().multiply(BigDecimal.valueOf(days)) ;
        if (extraPrice != null){
            totalPrice = totalPrice.add(extraPrice);
        }

        return totalPrice;
    }
}
