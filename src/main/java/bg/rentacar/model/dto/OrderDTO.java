package bg.rentacar.model.dto;

import bg.rentacar.constant.OrderConstants;
import bg.rentacar.model.enums.RentOrderStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

public class OrderDTO {

    private Long id;

    @NotBlank(message = OrderConstants.ORDER_PICK_UP_LOCATION_EMPTY)
    private String location;

    @FutureOrPresent(message = OrderConstants.ORDER_PICK_UP_DATE_PAST)
    @NotNull(message = OrderConstants.ORDER_PICK_UP_DATE_EMPTY)
    private LocalDate pickUpDate;

    @FutureOrPresent(message = OrderConstants.ORDER_DROP_OFF_DATE_PAST)
    @NotNull(message = OrderConstants.ORDER_DROP_OFF_DATE_EMPTY)
    private LocalDate dropOffDate;

    @NotNull(message = OrderConstants.ORDER_PICK_UP_TIME_EMPTY)
    private LocalTime pickUpTime;

    @NotNull(message = OrderConstants.ORDER_DROP_OFF_TIME_EMPTY)
    private LocalTime dropOffTime;

    @NotBlank(message = OrderConstants.ORDER_RETURN_LOCATION_EMPTY)
    private String returnLocation;

    @Enumerated(EnumType.STRING)
    private RentOrderStatus status;

    private BigDecimal totalPrice;

    @NotNull(message = OrderConstants.ORDER_CAR_EMPTY)
    private Long carId;

    private Long extraId;

    private String user;

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

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public Long getExtraId() {
        return extraId;
    }

    public void setExtraId(Long extraId) {
        this.extraId = extraId;
    }

    public RentOrderStatus getStatus() {
        return status;
    }

    public void setStatus(RentOrderStatus status) {
        this.status = status;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
