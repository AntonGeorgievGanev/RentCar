package bg.rentacar.service.car.impl;

import bg.rentacar.model.dto.AddCarDTO;
import bg.rentacar.model.entity.Car;
import bg.rentacar.repository.CarRepository;
import bg.rentacar.service.car.CarService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;
    private final ModelMapper mapper;

    private final RestClient carsRestClient;

    public CarServiceImpl(CarRepository carRepository, ModelMapper mapper, RestClient carsRestClient) {
        this.carRepository = carRepository;
        this.mapper = mapper;
        this.carsRestClient = carsRestClient;
    }
    @Override
    public void addNewCar(AddCarDTO addCarDTO) {
        Car car = mapper.map(addCarDTO, Car.class);
        car.setAvailable(true);
        carRepository.save(car);
    }

    @Override
    public AddCarDTO getCarById(Long id) {
        Optional<Car> carOpt = carRepository.findById(id);
        if (carOpt.isEmpty()){
            //TODO: errorHandling
            throw new NoSuchElementException("Sorry! There is no such car!");
        }

        return mapper.map(carOpt.get(), AddCarDTO.class);
    }

    @Override
    public List<AddCarDTO> getAllCars() {
        List<Car> allCars = carRepository.findAll();
        return allCars.stream().map(car -> mapper.map(car, AddCarDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteCar(Long id) {
        carRepository.deleteById(id);
    }

}
