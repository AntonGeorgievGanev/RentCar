package bg.rentacar.model.dto;

import bg.rentacar.constant.CarConstants;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public class EditCarDTO {
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

    @NotNull(message = CarConstants.CAR_EMPTY_PRICE)
    @Positive(message = CarConstants.CAR_NEGATIVE_PRICE)
    private BigDecimal pricePerDay;

    @NotNull(message = CarConstants.CAR_EMPTY_FUEL)
    @Positive(message = CarConstants.CAR_NEGATIVE_FUEL)
    private Integer fuelConsumption;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public BigDecimal getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(BigDecimal pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getFuelConsumption() {
        return fuelConsumption;
    }

    public void setFuelConsumption(Integer fuelConsumption) {
        this.fuelConsumption = fuelConsumption;
    }
}
