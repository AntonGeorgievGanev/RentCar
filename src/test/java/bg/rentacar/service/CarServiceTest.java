package bg.rentacar.service;

import bg.rentacar.exception.ObjectNotFound;
import bg.rentacar.model.dto.AllCarsDTO;
import bg.rentacar.model.dto.CarDTO;
import bg.rentacar.model.dto.CarsByCategoryDTO;
import bg.rentacar.model.dto.EditCarDTO;
import bg.rentacar.model.entity.Car;
import bg.rentacar.model.enums.CarCategory;
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
    }

    @Test
    void addNewCar_ShouldSaveCar() {
        CarDTO carDTO = new CarDTO();
        Car car;

        toTest.addNewCar(carDTO);

        verify(mockCarRepository).save(carArgumentCaptor.capture());
        car = carArgumentCaptor.getValue();
        Assertions.assertTrue(car.isAvailable());
    }

    @Test
    void getCarById_ShouldReturnCarDTO_WhenCarExists() {
        Car car = new Car();
        car.setBrand("Audi");
        car.setModel("A4");
        car.setYear(2020);
        car.setPricePerDay(BigDecimal.valueOf(200));

        when(mockCarRepository.findById(1L)).thenReturn(Optional.of(car));

        CarDTO result = toTest.getCarById(1L);

        Assertions.assertEquals(car.getBrand(), result.getBrand());
        Assertions.assertEquals(car.getModel(), result.getModel());
        Assertions.assertEquals(car.getYear(), result.getYear());
        Assertions.assertEquals(car.getPricePerDay(), result.getPricePerDay());
    }

    @Test
    void getCarById_ShouldThrowException_WhenCarDoesNotExist() {
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
        Car car = new Car();
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
        Car car = new Car();
        car.setBrand("Audi");
        car.setModel("A4");
        car.setYear(2020);
        car.setPricePerDay(BigDecimal.valueOf(200));
        car.setFuelConsumption(4);

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
        Car car = new Car();
        car.setBrand("Test");

        when(mockCarRepository.findById(1L)).thenReturn(Optional.of(car));
        EditCarDTO result = toTest.getCarForEdit(1L);
        Assertions.assertEquals(car.getBrand(), result.getBrand());
    }

}
