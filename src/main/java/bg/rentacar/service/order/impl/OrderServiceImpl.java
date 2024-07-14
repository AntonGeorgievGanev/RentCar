package bg.rentacar.service.order.impl;

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
import bg.rentacar.service.order.OrderService;
import bg.rentacar.web.LoginController;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    private final CarRepository carRepository;

    private final UserRepository userRepository;

    private final ExtraRepository extraRepository;

    private final ModelMapper mapper;

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    public OrderServiceImpl(OrderRepository orderRepository, CarRepository carRepository,
                            UserRepository userRepository, ExtraRepository extraRepository, ModelMapper mapper) {
        this.orderRepository = orderRepository;
        this.carRepository = carRepository;
        this.userRepository = userRepository;
        this.extraRepository = extraRepository;
        this.mapper = mapper;
    }

    @Override
    public void registerOrder(OrderDTO orderDTO, String name) {
        Order order = customMapDtoToEntity(orderDTO);

        Car car = carRepository.findById(orderDTO.getCarId())
                .orElseThrow(() -> new ObjectNotFound("This object cannot be found."));
        User user = userRepository.findByUsername(name)
                .orElseThrow(() -> new ObjectNotFound("This object cannot be found."));

        if (orderDTO.getExtraId() != null) {
            Extra extra = extraRepository.findById(orderDTO.getExtraId())
                    .orElseThrow(() -> new ObjectNotFound("This object cannot be found."));
            order.setExtra(extra);
        }

        order.setUser(user);
        order.setCar(car);
        order.setStatus(RentOrderStatus.PENDING);

        car.setAvailable(false);
        carRepository.save(car);

        order.setTotalPrice(order.calculateTotalPrice());
        orderRepository.save(order);
    }

    @Override
    public Order getOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFound("This object cannot be found."));
    }

    @Override
    public AllUserOrdersDTO getAllUserOrders(Principal principal) {
        User user = userRepository.findByUsername(principal.getName())
                .orElseThrow(() -> new ObjectNotFound("This object cannot be found."));
        List<Order> userOrdersFromDb = orderRepository.findAll().stream()
                .filter(order -> order.getUser().getId().equals(user.getId())).toList();

        List<OrderDTO> userOrdersDTO = new ArrayList<>();

        userOrdersFromDb.forEach(orderDb -> {
            OrderDTO orderDTO = mapper.map(orderDb, OrderDTO.class);
            orderDTO.setTotalPrice(orderDb.getTotalPrice());
            userOrdersDTO.add(orderDTO);
        });

        return new AllUserOrdersDTO(userOrdersDTO);
    }

    @Override
    public AllOrdersByStatus getAllOrdersByStatus() {
        List<OrderDTO> pendingOrdersDTO = new ArrayList<>();
        List<OrderDTO> approvedOrdersDTO = new ArrayList<>();
        List<OrderDTO> canceledOrdersDTO = new ArrayList<>();
        List<OrderDTO> finishedOrdersDTO = new ArrayList<>();

        orderRepository.findAll().stream().filter(order -> order.getStatus().equals(RentOrderStatus.PENDING))
                .forEach(orderFromDb -> {
                    OrderDTO orderDTO = mapper.map(orderFromDb, OrderDTO.class);
                    orderDTO.setTotalPrice(orderFromDb.getTotalPrice());
                    orderDTO.setUser(orderFromDb.getUser().getUsername());
                    pendingOrdersDTO.add(orderDTO);
                });

        orderRepository.findAll().stream().filter(order -> order.getStatus().equals(RentOrderStatus.APPROVED))
                .forEach(orderFromDb -> {
                    OrderDTO orderDTO = mapper.map(orderFromDb, OrderDTO.class);
                    orderDTO.setTotalPrice(orderFromDb.getTotalPrice());
                    orderDTO.setUser(orderFromDb.getUser().getUsername());
                    approvedOrdersDTO.add(orderDTO);
                });

        orderRepository.findAll().stream().filter(order -> order.getStatus().equals(RentOrderStatus.CANCELED))
                .forEach(orderFromDb -> {
                    OrderDTO orderDTO = mapper.map(orderFromDb, OrderDTO.class);
                    orderDTO.setTotalPrice(orderFromDb.getTotalPrice());
                    orderDTO.setUser(orderFromDb.getUser().getUsername());
                    canceledOrdersDTO.add(orderDTO);
                });

        orderRepository.findAll().stream().filter(order -> order.getStatus().equals(RentOrderStatus.FINISHED))
                .forEach(orderFromDb -> {
                    OrderDTO orderDTO = mapper.map(orderFromDb, OrderDTO.class);
                    orderDTO.setTotalPrice(orderFromDb.getTotalPrice());
                    orderDTO.setUser(orderFromDb.getUser().getUsername());
                    finishedOrdersDTO.add(orderDTO);
                });


        return new AllOrdersByStatus(pendingOrdersDTO, approvedOrdersDTO, canceledOrdersDTO, finishedOrdersDTO);
    }

    @Override
    public void approveOrder(Long id) {
        Optional<Order> optionalOrder = orderRepository.findById(id);
        if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
            order.setStatus(RentOrderStatus.APPROVED);
            orderRepository.save(order);
        }
    }

    @Override
    public void cancelOrder(Long id) {
        Optional<Order> optionalOrder = orderRepository.findById(id);
        if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
            Car car = order.getCar();
            order.setCar(null);
            car.setAvailable(true);
            carRepository.save(car);
            order.setStatus(RentOrderStatus.CANCELED);
            orderRepository.save(order);
        }
    }

    @Override
    public void finishOrder() {
        List<Order> approvedOrders = orderRepository.findAllByStatus(RentOrderStatus.APPROVED);
        if (!approvedOrders.isEmpty()) {
            LocalDateTime now = LocalDateTime.now();
//            LocalDateTime now = LocalDateTime.parse("2024-07-24T07:20"); testing

            for (Order order : approvedOrders) {
                LocalDateTime dropOffDateTime = LocalDateTime.of(order.getDropOffDate(), order.getDropOffTime());
                if (dropOffDateTime.isBefore(now)) {
                    Car car = order.getCar();
                    order.setStatus(RentOrderStatus.FINISHED);

                    order.setCar(null);
                    orderRepository.save(order);

                    car.setAvailable(true);
                    carRepository.save(car);
                    logger.info("Order with id: {} has been finished", order.getId());
                }
            }
        }
    }


    private Order customMapDtoToEntity(OrderDTO orderDTO) {
        Order order = new Order();
        order.setLocation(orderDTO.getLocation());
        order.setPickUpDate(orderDTO.getPickUpDate());
        order.setPickUpTime(orderDTO.getPickUpTime());
        order.setDropOffDate(orderDTO.getDropOffDate());
        order.setDropOffTime(orderDTO.getDropOffTime());
        order.setReturnLocation(orderDTO.getReturnLocation());
        return order;
    }
}
