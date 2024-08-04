package bg.rentacar.web;

import bg.rentacar.model.entity.Car;
import bg.rentacar.model.entity.Order;
import bg.rentacar.model.entity.User;
import bg.rentacar.model.enums.CarCategory;
import bg.rentacar.model.enums.EngineType;
import bg.rentacar.model.enums.Transmission;
import bg.rentacar.repository.CarRepository;
import bg.rentacar.repository.OrderRepository;
import bg.rentacar.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class AddOrderControllerIT {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setUp() throws Exception {
        Car car = new Car();
        car.setBrand("Test car");
        car.setModel("Test model");
        car.setYear(2020);
        car.setEngineType(EngineType.HYBRID);
        car.setCategory(CarCategory.COMPACT);
        car.setTransmission(Transmission.AUTOMATIC);
        car.setTrunkVolume(200);
        car.setFuelConsumption(4);
        car.setAvailable(true);
        car.setSeats(4);
        car.setPricePerDay(BigDecimal.valueOf(100));
        carRepository.save(car);

        User user = new User();
        user.setFirstName("Test user");
        user.setLastName("Test user");
        user.setEmail("test@test.com");
        user.setPassword("password");
        user.setAge(33);
        user.setUsername("test");
        user.setPhoneNumber("7676767676");
        userRepository.save(user);
    }

    @AfterEach
    void clean(){
        orderRepository.deleteAll();
        userRepository.deleteAll();
        carRepository.deleteAll();
    }

    @Test
    @WithMockUser(username = "test", roles = {"USER"})
    public void testGetAddOrder() throws Exception {
        mockMvc.perform(get("/book-car"))
                .andExpect(status().isOk())
                .andExpect(view().name("book-car"));
    }

    @Test
    @WithMockUser(username = "test", roles = {"USER"})
    public void testAddOrder() throws Exception {
        mockMvc.perform(post("/book-car")
                .param("location", "Test location")
                .param("pickUpDate", "2024-10-05")
                .param("pickUpTime", "10:00")
                .param("dropOffDate", "2024-10-10")
                .param("dropOffTime", "10:00")
                .param("returnLocation", "Test return location")
                .param("carId", "1")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect((redirectedUrl("/")));

        Assertions.assertTrue(orderRepository.findById(1L).isPresent());
        Order order = orderRepository.findById(1L).get();

        Assertions.assertEquals("PENDING", order.getStatus().name());
        Assertions.assertEquals("Test location", order.getLocation());
        Assertions.assertEquals(LocalDate.parse("2024-10-05"), order.getPickUpDate());
        Assertions.assertEquals("Test return location", order.getReturnLocation());
        Assertions.assertEquals("Test car", order.getCar().getBrand());
        Assertions.assertEquals("test@test.com", order.getUser().getEmail());
    }

    @Test
    @WithMockUser(username = "test", roles = {"USER"})
    public void testAddOrder_invalid() throws Exception {
        mockMvc.perform(post("/book-car")
                        .param("location", "")
                        .param("pickUpDate", "2020-10-05")
                        .param("pickUpTime", "10:00")
                        .param("dropOffDate", "2020-10-10")
                        .param("dropOffTime", "10:00")
                        .param("returnLocation", "Test return location")
                        .param("carId", "1")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect((redirectedUrl("/book-car")));
    }
}
