package bg.rentacar.service.car.impl;

import bg.rentacar.model.dto.AllCarsDTO;
import bg.rentacar.model.dto.CarDTO;
import bg.rentacar.model.dto.CarsByCategoryDTO;
import bg.rentacar.model.entity.Car;
import bg.rentacar.model.entity.Image;
import bg.rentacar.model.enums.CarCategory;
import bg.rentacar.repository.CarRepository;
import bg.rentacar.repository.ImageRepository;
import bg.rentacar.service.car.CarService;
import bg.rentacar.service.cloudinary.CloudinaryService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;

    private final ModelMapper mapper;

    private final RestClient carsRestClient;

    private final ImageRepository imageRepository;

    private final CloudinaryService cloudinaryService;

    public CarServiceImpl(CarRepository carRepository, ModelMapper mapper,
                          RestClient carsRestClient, ImageRepository imageRepository,
                          CloudinaryService cloudinaryService) {
        this.carRepository = carRepository;
        this.mapper = mapper;
        this.carsRestClient = carsRestClient;
        this.imageRepository = imageRepository;
        this.cloudinaryService = cloudinaryService;
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
    public AllCarsDTO getAllAvailableCarsDTO() {
        AllCarsDTO allCarsDTO = new AllCarsDTO();
        List<Car> carsFromDb = carRepository.findAllByisAvailable(true);
        List<CarDTO> carsDTO = carsFromDb.stream().map(car -> mapper.map(car, CarDTO.class)).toList();
        allCarsDTO.setAllCarsDTO(carsDTO);
        return allCarsDTO;
    }

    @Override
    public void addCarWithImage(CarDTO carDTO, MultipartFile file) throws IOException {
        Car car = mapper.map(carDTO, Car.class);
        Image image = new Image();

        if (!file.isEmpty()) {
            String imageUrl = cloudinaryService.uploadImage(file);
            image.setName(file.getOriginalFilename());
            image.setLocation(imageUrl);
            imageRepository.save(image);

        } else {
            image = imageRepository.findById(1L).get();
        }

        car.setImage(image);
        car.setAvailable(true);
        carRepository.save(car);
    }

    @Override
    public CarsByCategoryDTO getCarsByCategory() {

        List<CarDTO> compactCarsDTO = carRepository.findAll().stream()
                .filter(car -> car.getCategory().equals(CarCategory.COMPACT))
                .map(carFromDb -> mapper.map(carFromDb, CarDTO.class)).toList();

        List<CarDTO> estateCarsDTO = carRepository.findAll().stream()
                .filter(car -> car.getCategory().equals(CarCategory.ESTATE))
                .map(carFromDb -> mapper.map(carFromDb, CarDTO.class)).toList();

        List<CarDTO> suvCarsDTO = carRepository.findAll().stream()
                .filter(car -> car.getCategory().equals(CarCategory.SUV))
                .map(carFromDb -> mapper.map(carFromDb, CarDTO.class)).toList();


        return new CarsByCategoryDTO(compactCarsDTO, estateCarsDTO, suvCarsDTO);
    }
}
