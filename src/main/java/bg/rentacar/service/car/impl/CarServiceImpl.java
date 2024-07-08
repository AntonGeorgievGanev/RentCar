package bg.rentacar.service.car.impl;

import bg.rentacar.model.dto.CarDTO;
import bg.rentacar.model.dto.AllCarsDTO;
import bg.rentacar.model.entity.Car;
import bg.rentacar.model.entity.Image;
import bg.rentacar.repository.CarRepository;
import bg.rentacar.repository.ImageRepository;
import bg.rentacar.service.car.CarService;
import bg.rentacar.service.image.ImageService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;

    private final ModelMapper mapper;

    private final ImageService imageService;

    private final RestClient carsRestClient;

    private final ImageRepository imageRepository;

    public CarServiceImpl(CarRepository carRepository, ModelMapper mapper, ImageService imageService, RestClient carsRestClient, ImageRepository imageRepository) {
        this.carRepository = carRepository;
        this.mapper = mapper;
        this.imageService = imageService;
        this.carsRestClient = carsRestClient;
        this.imageRepository = imageRepository;
    }

    @Override
    public void addNewCar(CarDTO addCarDTO) {
        Car car = mapper.map(addCarDTO, Car.class);
        car.setAvailable(true);
        carRepository.save(car);
    }

    @Override
    public CarDTO getCarById(Long id) {
        Optional<Car> carOpt = carRepository.findById(id);
        if (carOpt.isEmpty()) {
            //TODO: errorHandling
            throw new NoSuchElementException("Sorry! There is no such car!");
        }

        return mapper.map(carOpt.get(), CarDTO.class);
    }

    @Override
    public List<CarDTO> getAllCarsRest() {
        List<Car> allCars = carRepository.findAll();
        return allCars.stream().map(car -> mapper.map(car, CarDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteCar(Long id) {
        carRepository.deleteById(id);
    }

    @Override
    public AllCarsDTO getAllCarsDTO() {
        AllCarsDTO allCarsDTO = new AllCarsDTO();
        List<Car> carsFromDb = carRepository.findAllByisAvailable(true);
        List<CarDTO> carsDTO = carsFromDb.stream().map(car -> mapper.map(car, CarDTO.class)).toList();
        allCarsDTO.setAllCarsDTO(carsDTO);
        return allCarsDTO;
    }

    @Override
    public void addCarWithImage(CarDTO carDTO, MultipartFile file) throws IOException {
        Car car = mapper.map(carDTO, Car.class);

        String uploadDirectory = "src/main/resources/static/images/cars/" + carDTO.getBrand() + "_" + carDTO.getModel();
        Image image = new Image();
        image.setName(UUID.randomUUID() + "_" + file.getOriginalFilename());
        image.setLocation(imageService.save(uploadDirectory, file));
        imageRepository.save(image);

        car.setImage(image);
        car.setAvailable(true);

        carRepository.save(car);
    }
}
