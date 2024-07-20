package bg.rentacar.service;

import bg.rentacar.model.dto.CarDTO;
import bg.rentacar.model.dto.AllCarsDTO;
import bg.rentacar.model.dto.CarsByCategoryDTO;
import bg.rentacar.model.dto.EditCarDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface CarService {
    void addNewCar(CarDTO addCarDTO);
    CarDTO getCarById(Long id);
    void deleteCar(Long id);
    AllCarsDTO getAllAvailableCarsDTO();
    void addCarWithImage(CarDTO carDTO, MultipartFile file) throws IOException;
    CarsByCategoryDTO getCarsByCategory();
    void editCar(Long id, EditCarDTO editCarDTO);
    EditCarDTO getCarForEdit(Long id);
}
