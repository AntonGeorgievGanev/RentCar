package bg.rentacar.service.car;

import bg.rentacar.model.dto.CarDTO;
import bg.rentacar.model.dto.AllCarsDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface CarService {
    void addNewCar(CarDTO addCarDTO);
    CarDTO getCarById(Long id);
    List<CarDTO> getAllCarsRest();
    void deleteCar(Long id);
    AllCarsDTO getAllCarsDTO();
    void addCarWithImage(CarDTO carDTO, MultipartFile file) throws IOException;
}
