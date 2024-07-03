package bg.rentacar.service.car.impl;

import bg.rentacar.model.dto.AddCarDTO;
import bg.rentacar.model.entity.Car;
import bg.rentacar.repository.CarRepository;
import bg.rentacar.service.car.CarService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;
    private final ModelMapper mapper;

    public CarServiceImpl(CarRepository carRepository, ModelMapper mapper) {
        this.carRepository = carRepository;
        this.mapper = mapper;
    }
    @Override
    public void addNewCar(AddCarDTO addCarDTO) {
        Car car = mapper.map(addCarDTO, Car.class);
        car.setAvailable(true);
        carRepository.save(car);
    }
}
