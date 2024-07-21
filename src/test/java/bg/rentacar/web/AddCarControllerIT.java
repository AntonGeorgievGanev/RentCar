package bg.rentacar.web;

import bg.rentacar.model.dto.CarDTO;
import bg.rentacar.model.enums.CarCategory;
import bg.rentacar.model.enums.EngineType;
import bg.rentacar.model.enums.Transmission;
import bg.rentacar.service.CarService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class AddCarControllerIT {

    @Autowired
    private MockMvc mockMvc;

    private CarDTO validCarDTO;

    @MockBean
    private CarService carService;

    @BeforeEach
    void setUp() {
        validCarDTO = new CarDTO();
        validCarDTO.setBrand("Toyota");
        validCarDTO.setModel("Corolla");
        validCarDTO.setYear(2020);
        validCarDTO.setPricePerDay(BigDecimal.valueOf(50));
        validCarDTO.setEngineType(EngineType.HYBRID);
        validCarDTO.setTransmission(Transmission.AUTOMATIC);
        validCarDTO.setCategory(CarCategory.COMPACT);
        validCarDTO.setSeats(4);
        validCarDTO.setFuelConsumption(5);
        validCarDTO.setTrunkVolume(400);
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void testGetAddCar() throws Exception {
        mockMvc.perform(get("/add-car"))
                .andExpect(status().isOk())
                .andExpect(view().name("add-car"))
                .andExpect(model().attributeExists("addCarDTO"));
    }
}
