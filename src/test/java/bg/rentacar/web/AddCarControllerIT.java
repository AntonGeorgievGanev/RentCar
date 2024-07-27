package bg.rentacar.web;

import bg.rentacar.model.dto.CarDTO;
import bg.rentacar.model.enums.CarCategory;
import bg.rentacar.model.enums.EngineType;
import bg.rentacar.model.enums.Transmission;
import bg.rentacar.repository.CarRepository;
import bg.rentacar.service.CarService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class AddCarControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CarRepository carRepository;

    private CarDTO addCarDTO;

    @MockBean
    private CarService carService;

    @BeforeEach
    void setUp() {
        addCarDTO = new CarDTO();
        addCarDTO.setBrand("Test");
        addCarDTO.setModel("Test");
        addCarDTO.setYear(2020);
        addCarDTO.setPricePerDay(BigDecimal.valueOf(100));
        addCarDTO.setEngineType(EngineType.HYBRID);
        addCarDTO.setTransmission(Transmission.AUTOMATIC);
        addCarDTO.setCategory(CarCategory.COMPACT);
        addCarDTO.setSeats(4);
        addCarDTO.setFuelConsumption(5);
        addCarDTO.setTrunkVolume(400);
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void testGetAddCar() throws Exception {
        mockMvc.perform(get("/add-car"))
                .andExpect(status().isOk())
                .andExpect(view().name("add-car"))
                .andExpect(model().attributeExists("addCarDTO"));
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void testAddCar() throws Exception {
        MockMultipartFile carImage = new MockMultipartFile("carImage", "car.jpg", "image/jpeg", "some-image".getBytes());

        mockMvc.perform(multipart("/add-car")
                        .file(carImage)
                        .param("id", "1")
                        .param("brand", addCarDTO.getBrand())
                        .param("model", addCarDTO.getModel())
                        .param("year", addCarDTO.getYear().toString())
                        .param("pricePerDay", addCarDTO.getPricePerDay().toString())
                        .param("engineType", addCarDTO.getEngineType().name())
                        .param("transmission", addCarDTO.getTransmission().name())
                        .param("category", addCarDTO.getCategory().name())
                        .param("seats", addCarDTO.getSeats().toString())
                        .param("fuelConsumption", addCarDTO.getFuelConsumption().toString())
                        .param("trunkVolume", addCarDTO.getTrunkVolume().toString())
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/fleet"));

        verify(carService, times(1)).addCarWithImage(any(CarDTO.class), any(MultipartFile.class));
    }
}
