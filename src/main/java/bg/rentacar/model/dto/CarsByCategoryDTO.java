package bg.rentacar.model.dto;

import java.util.List;

public class CarsByCategoryDTO {
    private List<CarDTO> compactCars;
    private List<CarDTO> estateCars;
    private List<CarDTO> suvCars;

    public CarsByCategoryDTO() {
    }

    public CarsByCategoryDTO(List<CarDTO> compactCars, List<CarDTO> estateCars, List<CarDTO> suvCars) {
        this.compactCars = compactCars;
        this.estateCars = estateCars;
        this.suvCars = suvCars;
    }

    public List<CarDTO> getCompactCars() {
        return compactCars;
    }

    public void setCompactCars(List<CarDTO> compactCars) {
        this.compactCars = compactCars;
    }

    public List<CarDTO> getEstateCars() {
        return estateCars;
    }

    public void setEstateCars(List<CarDTO> estateCars) {
        this.estateCars = estateCars;
    }

    public List<CarDTO> getSuvCars() {
        return suvCars;
    }

    public void setSuvCars(List<CarDTO> suvCars) {
        this.suvCars = suvCars;
    }
}
