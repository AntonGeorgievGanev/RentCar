package bg.rentacar.model.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;

public class OrderDTO {
    @NotBlank(message = "Please enter location for pick up!")
    private String location;

    @FutureOrPresent(message = "Pick up date cannot be in the past!")
    @NotNull(message = "Please select a date for pick up!")
    private LocalDate pickUpDate;

    @FutureOrPresent(message = "Drop off date cannot be in the past!")
    @NotNull(message = "Please select a date to return!")
    private LocalDate dropOffDate;

    @NotNull(message = "Please select a time for pick up!")
    private LocalTime pickUpTime;

    @NotNull(message = "Please select a time return!")
    private LocalTime dropOffTime;

    @NotBlank(message = "Please enter return location!")
    private String returnLocation;

    @NotNull(message = "Select a car!")
    private Long carId;

    private Long extraId;

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
}
