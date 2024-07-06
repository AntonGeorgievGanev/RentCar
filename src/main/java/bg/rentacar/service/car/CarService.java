package bg.rentacar.service.car;

import bg.rentacar.model.dto.AddCarDTO;
import bg.rentacar.model.dto.AllCarsDTO;

import java.util.List;

public interface CarService {

    void addNewCar(AddCarDTO addCarDTO);
    AddCarDTO getCarById(Long id);
    List<AddCarDTO> getAllCarsRest();
    void deleteCar(Long id);

    AllCarsDTO getAllCarsDTO();
}
