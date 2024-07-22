package bg.rentacar.service;

import bg.rentacar.exception.ObjectNotFound;
import bg.rentacar.model.dto.AllCarsDTO;
import bg.rentacar.model.dto.CarDTO;
import bg.rentacar.model.dto.CarsByCategoryDTO;
import bg.rentacar.model.dto.EditCarDTO;
import bg.rentacar.model.entity.Car;
import bg.rentacar.model.enums.CarCategory;
import bg.rentacar.model.enums.EngineType;
import bg.rentacar.model.enums.Transmission;
import bg.rentacar.repository.CarRepository;
import bg.rentacar.repository.ImageRepository;
import bg.rentacar.service.impl.CarServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class CarServiceTest {

    private CarServiceImpl toTest;

    private Car car;

    private CarDTO carDTO;

    @Mock
    private CarRepository mockCarRepository;

    @Mock
    private ImageRepository mockImageRepository;

    @Mock
    private CloudinaryService mockCloudinaryService;

    @Captor
    private ArgumentCaptor<Car> carArgumentCaptor;


    @BeforeEach
    void setUp() {

        toTest = new CarServiceImpl(
                mockCarRepository,
                new ModelMapper(),
                mockImageRepository,
                mockCloudinaryService
        );

        car = new Car();
        car.setBrand("Vw");
        car.setModel("Golf");
        car.setYear(2020);
        car.setEngineType(EngineType.DIESEL);
        car.setCategory(CarCategory.COMPACT);
        car.setTransmission(Transmission.MANUAL);
        car.setSeats(4);
        car.setFuelConsumption(5);
        car.setTrunkVolume(350);
        car.setPricePerDay(BigDecimal.valueOf(120));

        carDTO = new CarDTO();
        carDTO.setBrand("Vw");
        carDTO.setModel("Golf");
        carDTO.setYear(2020);
        carDTO.setEngineType(EngineType.DIESEL);
        carDTO.setCategory(CarCategory.COMPACT);
        carDTO.setTransmission(Transmission.MANUAL);
        carDTO.setSeats(4);
        carDTO.setFuelConsumption(5);
        carDTO.setTrunkVolume(350);
        carDTO.setPricePerDay(BigDecimal.valueOf(120));
    }

    @Test
    void addNewCar_ShouldSaveCar() {

        toTest.addNewCar(carDTO);

        verify(mockCarRepository).save(carArgumentCaptor.capture());
        car = carArgumentCaptor.getValue();
        Assertions.assertTrue(car.isAvailable());
    }

    @Test
    void getCarById_ReturnCarDTO_WhenCarExists() {

        when(mockCarRepository.findById(1L)).thenReturn(Optional.of(car));

        CarDTO result = toTest.getCarById(1L);

        Assertions.assertEquals(car.getBrand(), result.getBrand());
        Assertions.assertEquals(car.getModel(), result.getModel());
        Assertions.assertEquals(car.getYear(), result.getYear());
        Assertions.assertEquals(car.getPricePerDay(), result.getPricePerDay());
    }

    @Test
    void getCarById_ThrowException_WhenCarDoesNotExist() {
        when(mockCarRepository.findById(1L)).thenReturn(Optional.empty());

        Assertions.assertThrows(ObjectNotFound.class, () -> toTest.getCarById(1L));
    }

    @Test
    void deleteCar_ShouldCallRepositoryDelete() {
        toTest.deleteCar(1L);

        verify(mockCarRepository, times(1)).deleteById(1L);
    }

    @Test
    void getAllAvailableCarsDTO_ShouldReturnAllAvailableCars() {
       car.setAvailable(true);
        List<Car> cars = List.of(car);
        when(mockCarRepository.findAllByisAvailable(true)).thenReturn(cars);

        AllCarsDTO result = toTest.getAllAvailableCarsDTO();

        Assertions.assertEquals(1, result.getAllCarsDTO().size());
    }

    @Test
    void getCarsByCategory_ShouldReturnCarsByCategory() {
        Car compactCar = new Car();
        compactCar.setCategory(CarCategory.COMPACT);
        Car estateCar = new Car();
        estateCar.setCategory(CarCategory.ESTATE);
        Car suvCar = new Car();
        suvCar.setCategory(CarCategory.SUV);
        List<Car> cars = List.of(compactCar, estateCar, suvCar);

        when(mockCarRepository.findAll()).thenReturn(cars);

        CarsByCategoryDTO result = toTest.getCarsByCategory();
        Assertions.assertEquals(1, result.getCompactCars().size());
        Assertions.assertEquals(1, result.getEstateCars().size());
        Assertions.assertEquals(1, result.getSuvCars().size());
    }

    @Test
    void testEditCar_updatesCar(){
        when(mockCarRepository.findById(1L)).thenReturn(Optional.of(car));
        EditCarDTO editCarDTO = new EditCarDTO();
        editCarDTO.setYear(2021);
        editCarDTO.setPricePerDay(BigDecimal.valueOf(220));

        toTest.editCar(1L, editCarDTO);
        Assertions.assertEquals(editCarDTO.getYear(), car.getYear());
        Assertions.assertEquals(editCarDTO.getPricePerDay(), car.getPricePerDay());
    }

    @Test
    void getCarForEdit_ShouldReturnEditCarDTO_WhenCarExists() {
        when(mockCarRepository.findById(1L)).thenReturn(Optional.of(car));
        EditCarDTO result = toTest.getCarForEdit(1L);
        Assertions.assertEquals(car.getBrand(), result.getBrand());
    }

}
