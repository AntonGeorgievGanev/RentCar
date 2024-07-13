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
    @Positive
    @Min(value = 2017, message = "We don`t offer cars before 2017!")
    private int year;

    @NotNull
    @Positive
    private BigDecimal pricePerDay;

    @NotNull
    @Positive
    private int fuelConsumption;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank(message = "Please enter brand!") @Size(min = 2, max = 40, message = "Brand length must be between 2 and 40 characters!") String getBrand() {
        return brand;
    }

    public void setBrand(@NotBlank(message = "Please enter brand!") @Size(min = 2, max = 40, message = "Brand length must be between 2 and 40 characters!") String brand) {
        this.brand = brand;
    }

    public @NotBlank(message = "Please enter model!") @Size(min = 2, max = 40, message = "Model length must be between 2 and 40 characters!") String getModel() {
        return model;
    }

    public void setModel(@NotBlank(message = "Please enter model!") @Size(min = 2, max = 40, message = "Model length must be between 2 and 40 characters!") String model) {
        this.model = model;
    }

    @NotNull(message = "Please enter year!")
    @Positive
    @Min(value = 2017, message = "We don`t offer cars before 2017!")
    public int getYear() {
        return year;
    }

    public void setYear(@NotNull(message = "Please enter year!") @Positive @Min(value = 2017, message = "We don`t offer cars before 2017!") int year) {
        this.year = year;
    }

    public @NotNull @Positive BigDecimal getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(@NotNull @Positive BigDecimal pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    @NotNull
    @Positive
    public int getFuelConsumption() {
        return fuelConsumption;
    }

    public void setFuelConsumption(@NotNull @Positive int fuelConsumption) {
        this.fuelConsumption = fuelConsumption;
    }
}
