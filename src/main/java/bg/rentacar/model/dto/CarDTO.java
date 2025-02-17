package bg.rentacar.model.dto;

import bg.rentacar.constant.CarConstants;
import bg.rentacar.model.entity.Image;
import bg.rentacar.model.enums.CarCategory;
import bg.rentacar.model.enums.EngineType;
import bg.rentacar.model.enums.Transmission;
import bg.rentacar.validation.annotation.FileImage;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

public class CarDTO {
    private Long id;
    @NotBlank(message = CarConstants.CAR_EMPTY_BRAND)
    @Size(min = 2, max = 40, message = CarConstants.CAR_BRAND_LENGTH)
    private String brand;

    @NotBlank(message = CarConstants.CAR_EMPTY_MODEL)
    @Size(min = 2, max = 40, message = CarConstants.CAR_MODEL_LENGTH)
    private String model;

    @NotNull(message = CarConstants.CAR_EMPTY_YEAR)
    @Positive(message = CarConstants.CAR_NEGATIVE_YEAR)
    @Min(value = 2017, message = CarConstants.CAR_MIN_YEAR)
    private Integer year;

    @NotNull(message = CarConstants.CAR_EMPTY_ENGINE_TYPE)
    @Enumerated(EnumType.STRING)
    private EngineType engineType;

    @NotNull(message = CarConstants.CAR_EMPTY_TRANSMISSION_TYPE)
    @Enumerated(EnumType.STRING)
    private Transmission transmission;

    @NotNull(message = CarConstants.CAR_EMPTY_CATEGORY)
    @Enumerated(EnumType.STRING)
    private CarCategory category;

    @NotNull(message = CarConstants.CAR_EMPTY_SEATS)
    @Min(value = 2, message = CarConstants.CAR_MIN_SEATS)
    @Max(value = 7, message = CarConstants.CAR_MAX_SEATS)
    private Integer seats;

    @NotNull(message = CarConstants.CAR_EMPTY_FUEL)
    @Positive(message = CarConstants.CAR_NEGATIVE_FUEL)
    private Integer fuelConsumption;

    @NotNull(message = CarConstants.CAR_EMPTY_TRUNK)
    @Positive(message = CarConstants.CAR_NEGATIVE_TRUNK)
    @Min(value = 100, message = CarConstants.CAR_MIN_TRUNK)
    @Max(value = 600, message = CarConstants.CAR_MAX_TRUNK)
    private Integer trunkVolume;

    @NotNull(message = CarConstants.CAR_EMPTY_PRICE)
    @Positive(message = CarConstants.CAR_NEGATIVE_PRICE)
    private BigDecimal pricePerDay;

    @FileImage
    private MultipartFile carImage;

    private Image imageLocation;

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

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getSeats() {
        return seats;
    }

    public void setSeats(Integer seats) {
        this.seats = seats;
    }

    public Integer getFuelConsumption() {
        return fuelConsumption;
    }

    public void setFuelConsumption(Integer fuelConsumption) {
        this.fuelConsumption = fuelConsumption;
    }

    public Integer getTrunkVolume() {
        return trunkVolume;
    }

    public void setTrunkVolume(Integer trunkVolume) {
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

    public MultipartFile getCarImage() {
        return carImage;
    }

    public void setCarImage(MultipartFile carImage) {
        this.carImage = carImage;
    }

    public Image getImageLocation() {
        return imageLocation;
    }

    public void setImageLocation(Image imageLocation) {
        this.imageLocation = imageLocation;
    }

    @Override
    public String toString() {
        return brand + " " + model + " | Year: " + year + " | Category: " + category + " | Engine: " + engineType + " | Seats: " + seats + " | Fuel consumption: " + fuelConsumption + " l/100 km | " +
                trunkVolume + " liters trunk | Price for day: " + pricePerDay + " $";
    }

}
