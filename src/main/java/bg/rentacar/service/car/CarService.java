package bg.rentacar.service.car;

import bg.rentacar.model.dto.CarDTO;
import bg.rentacar.model.dto.AllCarsDTO;

import java.util.List;

public interface CarService {

    void addNewCar(CarDTO addCarDTO);
    CarDTO getCarById(Long id);
    List<CarDTO> getAllCarsRest();
    void deleteCar(Long id);
    AllCarsDTO getAllCarsDTO();
}
