package bg.rentacar.web.rest;

import bg.rentacar.model.dto.CarDTO;
import bg.rentacar.service.car.CarService;
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
    public ResponseEntity<CarDTO> getCarById(@PathVariable Long id){
        return ResponseEntity.ok(carService.getCarById(id));
    }

    @GetMapping
    public ResponseEntity<List<CarDTO>> getAllCars(){
        return ResponseEntity.ok(carService.getAllCarsRest());
    }

    @PostMapping
    public ResponseEntity<CarDTO> addCar(@RequestBody CarDTO addCarDTO){
        carService.addNewCar(addCarDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CarDTO> deleteCar(@PathVariable Long id){
        carService.deleteCar(id);
        return ResponseEntity.ok().build();
    }
}
