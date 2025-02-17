package bg.rentacar.model.dto;

import bg.rentacar.constant.ExtraConstants;
import bg.rentacar.model.entity.Extra;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public class ExtraDTO {
    private Long extraId;
    @NotBlank(message = ExtraConstants.EXTRA_NAME_EMPTY)
    @Size(min = 3, max = 30, message = ExtraConstants.EXTRA_NAME_LENGTH)
    private String name;

    @NotNull(message = ExtraConstants.EXTRA_PRICE_EMPTY)
    @Positive(message = ExtraConstants.EXTRA_PRICE_NEGATIVE)
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
