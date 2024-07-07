package bg.rentacar.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public class ExtraDTO {
    private Long extraId;
    @NotBlank(message = "Name must not be empty!")
    @Size(min = 3, max = 30, message = "Extra name length must be between 3 and 30 characters!")
    private String name;

    @NotNull(message = "Price must not be empty!")
    @Positive(message = "Price must be positive number!")
    private BigDecimal price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Long getExtraId() {
        return extraId;
    }

    public void setExtraId(Long extraId) {
        this.extraId = extraId;
    }

    @Override
    public String toString() {
        return name + " price: " + price + " $";
    }
}
