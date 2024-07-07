package bg.rentacar.service.order.impl;

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
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    private final CarRepository carRepository;

    private final UserRepository userRepository;

    private final ExtraRepository extraRepository;

    public OrderServiceImpl(OrderRepository orderRepository, CarRepository carRepository,
                            UserRepository userRepository, ExtraRepository extraRepository) {
        this.orderRepository = orderRepository;
        this.carRepository = carRepository;
        this.userRepository = userRepository;
        this.extraRepository = extraRepository;
    }

    @Override
    public void registerOrder(OrderDTO orderDTO, String name) {
        Order order = customMapDtoToEntity(orderDTO);

        Car car = carRepository.findById(orderDTO.getCarId()).get();
        User user = userRepository.findByUsername(name).get();

        if (orderDTO.getExtraId() != null) {
            Extra extra = extraRepository.findById(orderDTO.getExtraId()).get();
            order.setExtra(extra);
        }

        order.setUser(user);
        order.setCar(car);
        order.setStatus(RentOrderStatus.PENDING);

        car.setAvailable(false);
        carRepository.save(car);
        orderRepository.save(order);
    }

    @Override
    public Order getOrderById(Long id) {
        return orderRepository.findById(id).get();
    }

    private Order customMapDtoToEntity(OrderDTO orderDTO){
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
