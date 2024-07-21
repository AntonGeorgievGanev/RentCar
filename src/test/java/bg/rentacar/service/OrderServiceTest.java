package bg.rentacar.service;

import bg.rentacar.model.dto.OrderDTO;
import bg.rentacar.model.entity.Car;
import bg.rentacar.model.entity.Extra;
import bg.rentacar.model.entity.Order;
import bg.rentacar.model.entity.User;
import bg.rentacar.model.enums.RentOrderStatus;
import bg.rentacar.repository.CarRepository;
import bg.rentacar.repository.ExtraRepository;
import bg.rentacar.repository.OrderRepository;
import bg.rentacar.repository.UserRepository;
import bg.rentacar.service.impl.OrderServiceImpl;
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
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

    private OrderService toTest;

    @Mock
    private OrderRepository mockOrderRepository;

    @Mock
    private CarRepository mockCarRepository;

    @Mock
    private UserRepository mockUserRepository;

    @Mock
    private ExtraRepository mockExtraRepository;

    @Captor
    private ArgumentCaptor<Order> orderCaptor;

    @BeforeEach
    public void setUp() {
        toTest = new OrderServiceImpl(
                mockOrderRepository,
                mockCarRepository,
                mockUserRepository,
                mockExtraRepository,
                new ModelMapper()
        );
    }

    @Test
    void registerOrder_ShouldSaveOrder() {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setCarId(1L);
        orderDTO.setExtraId(1L);
        orderDTO.setLocation("Start");
        orderDTO.setPickUpDate(LocalDate.now());
        orderDTO.setPickUpTime(LocalTime.now());
        orderDTO.setDropOffDate(LocalDate.parse("2024-07-24"));
        orderDTO.setDropOffTime(LocalTime.now());
        orderDTO.setReturnLocation("End");

        Car car = new Car();
        car.setAvailable(true);
        car.setPricePerDay(BigDecimal.valueOf(100));

        User user = new User();

        Extra extra = new Extra();
        extra.setPrice(BigDecimal.valueOf(10));

        when(mockCarRepository.findById(orderDTO.getCarId())).thenReturn(Optional.of(car));
        when(mockUserRepository.findByUsername("username")).thenReturn(Optional.of(user));
        when(mockExtraRepository.findById(orderDTO.getExtraId())).thenReturn(Optional.of(extra));

        toTest.registerOrder(orderDTO, "username");

        verify(mockOrderRepository, times(1)).save(orderCaptor.capture());
        Order actualOrder = orderCaptor.getValue();
        Assertions.assertFalse(actualOrder.getCar().isAvailable());
        Assertions.assertEquals(RentOrderStatus.PENDING, actualOrder.getStatus());
        Assertions.assertEquals(car, actualOrder.getCar());
        Assertions.assertEquals(user, actualOrder.getUser());
        Assertions.assertEquals(extra, actualOrder.getExtra());
    }

}
