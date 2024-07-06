package bg.rentacar.web.rest;

import bg.rentacar.model.dto.AddCarDTO;
import bg.rentacar.service.car.CarService;
import com.google.gson.Gson;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cars")
public class CarController {

    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }


    @GetMapping("/{id}")
    public ResponseEntity<AddCarDTO> getCarById(@PathVariable Long id){
        return ResponseEntity.ok(carService.getCarById(id));
    }

    @GetMapping
    public ResponseEntity<List<AddCarDTO>> getAllCars(){
        return ResponseEntity.ok(carService.getAllCars());
    }

    @PostMapping
    public ResponseEntity<AddCarDTO> addCar(@RequestBody AddCarDTO addCarDTO){
        carService.addNewCar(addCarDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AddCarDTO> deleteCar(@PathVariable Long id){
        carService.deleteCar(id);
        return ResponseEntity.ok().build();
    }
}
