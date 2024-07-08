package bg.rentacar.model.dto;

import bg.rentacar.model.entity.Image;
import bg.rentacar.model.enums.CarCategory;
import bg.rentacar.model.enums.EngineType;
import bg.rentacar.model.enums.Transmission;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.math.BigDecimal;

public class CarDTO {
    private Long id;
    @NotBlank(message = "Please enter brand!")
    @Size(min = 2, max = 40, message = "Brand length must be between 2 and 40 characters!")
    private String brand;

    @NotBlank(message = "Please enter model!")
    @Size(min = 2, max = 40, message = "Model length must be between 2 and 40 characters!")
    private String model;

    @NotNull(message = "Please enter year!")
    @Positive
    @Min(value = 2017, message = "We don`t offer cars before 2017!")
    private int year;

    @NotNull(message = "Select engine type!")
    @Enumerated(EnumType.STRING)
    private EngineType engineType;

    @NotNull(message = "Select transmission type!")
    @Enumerated(EnumType.STRING)
    private Transmission transmission;

    @NotNull(message = "Select category!")
    @Enumerated(EnumType.STRING)
    private CarCategory category;

    @NotNull
    @Min(value = 2, message = "Minimum seats 2")
    @Max(value = 7, message = "Maximum seats 7")
    private int seats;

    @NotNull
    @Positive
    private int fuelConsumption;

    @NotNull
    @Positive
    @Min(value = 200, message = "Minimum trunk volume 200 liters!")
    @Max(value = 1000, message = "Maximum trunk volume 200 liters!")
    private int trunkVolume;

    @NotNull
    @Positive
    private BigDecimal pricePerDay;

    @NotNull(message = "Please upload an image!")
    private MultipartFile carImage;

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public EngineType getEngineType() {
        return engineType;
    }

    public void setEngineType(EngineType engineType) {
        this.engineType = engineType;
    }

    public Transmission getTransmission() {
        return transmission;
    }

    public void setTransmission(Transmission transmission) {
        this.transmission = transmission;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public int getFuelConsumption() {
        return fuelConsumption;
    }

    public void setFuelConsumption(int fuelConsumption) {
        this.fuelConsumption = fuelConsumption;
    }

    public int getTrunkVolume() {
        return trunkVolume;
    }

    public void setTrunkVolume(int trunkVolume) {
        this.trunkVolume = trunkVolume;
    }

    public BigDecimal getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(BigDecimal pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public CarCategory getCategory() {
        return category;
    }

    public void setCategory(CarCategory category) {
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotNull(message = "Please upload an image!") MultipartFile getCarImage() {
        return carImage;
    }

    public void setCarImage(@NotNull(message = "Please upload an image!") MultipartFile carImage) {
        this.carImage = carImage;
    }

    @Override
    public String toString() {
        return brand + " " + model + " | Year: " + year + " | Category: " + category + " | Engine: " + engineType + " | Seats: " + seats + " | Fuel consumption: " + fuelConsumption + " l/100 km | " +
                trunkVolume + " liters trunk | Price for day: " + pricePerDay + " $";
    }
}
