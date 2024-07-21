package bg.rentacar.service;

import bg.rentacar.exception.ObjectNotFound;
import bg.rentacar.model.dto.AllOrdersByStatus;
import bg.rentacar.model.dto.AllUserOrdersDTO;
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
import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
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

    @Test
    void registerOrder_ShouldThrowException_WhenCarNotFound() {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setCarId(1L);

        when(mockCarRepository.findById(orderDTO.getCarId())).thenReturn(Optional.empty());
        Assertions.assertThrows(ObjectNotFound.class, () -> toTest.registerOrder(orderDTO, "username"));
    }

    @Test
    void getOrderById_ReturnOrder_WhenOrderExists() {
        Order order = new Order();
        when(mockOrderRepository.findById(1L)).thenReturn(Optional.of(order));

        Order result = toTest.getOrderById(1L);
        Assertions.assertEquals(order, result);
    }

    @Test
    void getOrderById_ThrowException_WhenOrderDoesNotExist() {
        when(mockOrderRepository.findById(1L)).thenReturn(Optional.empty());

        Assertions.assertThrows(ObjectNotFound.class, () -> toTest.getOrderById(1L));
    }

    @Test
    void getAllOrdersByStatus_ReturnOrdersByStatus() {
        User user = new User();
        user.setId(1L);
        user.setUsername("Test");

        Order pendingOrder = new Order();
        pendingOrder.setStatus(RentOrderStatus.PENDING);
        pendingOrder.setTotalPrice(BigDecimal.valueOf(10));
        pendingOrder.setUser(user);

        Order approvedOrder = new Order();
        approvedOrder.setStatus(RentOrderStatus.APPROVED);
        approvedOrder.setTotalPrice(BigDecimal.valueOf(10));
        approvedOrder.setUser(user);

        Order canceledOrder = new Order();
        canceledOrder.setStatus(RentOrderStatus.CANCELED);
        canceledOrder.setTotalPrice(BigDecimal.valueOf(10));
        canceledOrder.setUser(user);

        Order finishedOrder = new Order();
        finishedOrder.setStatus(RentOrderStatus.FINISHED);
        finishedOrder.setTotalPrice(BigDecimal.valueOf(10));
        finishedOrder.setUser(user);

        List<Order> orders = List.of(pendingOrder, approvedOrder, canceledOrder, finishedOrder);

        when(mockOrderRepository.findAll()).thenReturn(orders);

        AllOrdersByStatus result = toTest.getAllOrdersByStatus();

        Assertions.assertEquals(1, result.getPendingOrders().size());
        Assertions.assertEquals(1, result.getApprovedOrders().size());
        Assertions.assertEquals(1, result.getCanceledOrders().size());
        Assertions.assertEquals(1, result.getFinishedOrders().size());
    }

    @Test
    void approveOrder_UpdatesOrderStatus() {
        Order order = new Order();
        order.setStatus(RentOrderStatus.PENDING);
        when(mockOrderRepository.findById(1L)).thenReturn(Optional.of(order));

        toTest.approveOrder(1L);

        verify(mockOrderRepository, times(1)).save(order);
        Assertions.assertEquals(RentOrderStatus.APPROVED, order.getStatus());
    }

    @Test
    void cancelOrder_UpdatesOrderStatusAndMakeCarAvailable() {
        Order order = new Order();
        Car car = new Car();
        car.setAvailable(false);
        order.setCar(car);
        order.setStatus(RentOrderStatus.PENDING);
        when(mockOrderRepository.findById(1L)).thenReturn(Optional.of(order));

        toTest.cancelOrder(1L);

        verify(mockOrderRepository, times(1)).save(order);
        verify(mockCarRepository, times(1)).save(car);
        Assertions.assertEquals(RentOrderStatus.CANCELED, order.getStatus());
        Assertions.assertTrue(car.isAvailable());
    }

    @Test
    void finishOrder_UpdateOrderStatusAndMakeCarAvailable() {
        Order order = new Order();
        Car car = new Car();
        order.setCar(car);
        order.setStatus(RentOrderStatus.APPROVED);
        order.setDropOffDate(LocalDateTime.now().minusDays(1).toLocalDate());
        order.setDropOffTime(LocalDateTime.now().minusDays(1).toLocalTime());
        List<Order> orders = List.of(order);

        when(mockOrderRepository.findAllByStatus(RentOrderStatus.APPROVED)).thenReturn(orders);

        toTest.finishOrder();

        verify(mockOrderRepository, times(1)).save(order);
        verify(mockCarRepository, times(1)).save(car);
        Assertions.assertEquals(RentOrderStatus.FINISHED, order.getStatus());
        Assertions.assertTrue(car.isAvailable());
    }

    @Test
    void deleteOrder_Deletes() {
        toTest.deleteOrder(1L);
        verify(mockOrderRepository, times(1)).deleteById(1L);
    }

    @Test
    void getAllUserOrders_ShouldReturnAllUserOrders() {
        User user = new User();
        user.setId(1L);
        Principal principal = () -> "test";
        Order order = new Order();
        order.setUser(user);
        List<Order> orders = List.of(order);

        when(mockUserRepository.findByUsername(principal.getName())).thenReturn(Optional.of(user));
        when(mockOrderRepository.findAll()).thenReturn(orders);

        AllUserOrdersDTO result = toTest.getAllUserOrders(principal);
        Assertions.assertEquals(1, result.getUserOrders().size());
    }
}
