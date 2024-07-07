package bg.rentacar.model.dto;

import java.util.ArrayList;
import java.util.List;

public class AllCarsDTO {
    private List<CarDTO> allCarsDTO;

    public AllCarsDTO() {
        this.allCarsDTO = new ArrayList<>();
    }

    public List<CarDTO> getAllCarsDTO() {
        return allCarsDTO;
    }

    public void setAllCarsDTO(List<CarDTO> allCarsDTO) {
        this.allCarsDTO = allCarsDTO;
    }
}
