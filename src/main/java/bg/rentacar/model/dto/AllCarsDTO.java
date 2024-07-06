package bg.rentacar.model.dto;

import java.util.ArrayList;
import java.util.List;

public class AllCarsDTO {
    private List<AddCarDTO> allCarsDTO;

    public AllCarsDTO() {
        this.allCarsDTO = new ArrayList<>();
    }

    public List<AddCarDTO> getAllCarsDTO() {
        return allCarsDTO;
    }

    public void setAllCarsDTO(List<AddCarDTO> allCarsDTO) {
        this.allCarsDTO = allCarsDTO;
    }
}
