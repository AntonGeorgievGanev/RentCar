package bg.rentacar.model.dto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public class EditCarDTO {
    private Long id;

    @NotBlank(message = "Please enter brand!")
    @Size(min = 2, max = 40, message = "Brand length must be between 2 and 40 characters!")
    private String brand;

    @NotBlank(message = "Please enter model!")
    @Size(min = 2, max = 40, message = "Model length must be between 2 and 40 characters!")
    private String model;

    @NotNull(message = "Please enter year!")
    @Positive(message = "Year cant be negative!")
    @Min(value = 2017, message = "We don`t offer cars before 2017!")
    private int year;

    @NotNull(message = "Please enter price!")
    @Positive(message = "Price cant be negative!")
    private BigDecimal pricePerDay;

    @NotNull(message = "Please enter fuel consumption!")
    @Positive(message = "Fuel consumption cant be negative!")
    private int fuelConsumption;

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

    public int getFuelConsumption() {
        return fuelConsumption;
    }

    public void setFuelConsumption(int fuelConsumption) {
        this.fuelConsumption = fuelConsumption;
    }
}
