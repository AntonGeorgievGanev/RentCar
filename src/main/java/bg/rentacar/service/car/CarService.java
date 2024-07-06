package bg.rentacar.service.car;

import bg.rentacar.model.dto.AddCarDTO;

import java.util.List;

public interface CarService {

    void addNewCar(AddCarDTO addCarDTO);
    AddCarDTO getCarById(Long id);
    List<AddCarDTO> getAllCars();
    void deleteCar(Long id);

}
