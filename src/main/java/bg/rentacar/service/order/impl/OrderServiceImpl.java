package bg.rentacar.service.order.impl;

import bg.rentacar.model.dto.OrderDTO;
import bg.rentacar.model.entity.Car;
import bg.rentacar.model.entity.Order;
import bg.rentacar.model.enums.RentOrderStatus;
import bg.rentacar.repository.CarRepository;
import bg.rentacar.repository.OrderRepository;
import bg.rentacar.service.order.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    private final CarRepository carRepository;

    private final ModelMapper mapper;

    public OrderServiceImpl(OrderRepository orderRepository, CarRepository carRepository, ModelMapper mapper) {
        this.orderRepository = orderRepository;
        this.carRepository = carRepository;
        this.mapper = mapper;
    }

    @Override
    public void registerOrder(OrderDTO orderDTO) {
        Order order = mapper.map(orderDTO, Order.class);
        Car car = carRepository.findById(orderDTO.getCarId()).get();
        order.setCar(car);
        order.setStatus(RentOrderStatus.PENDING);
        orderRepository.save(order);
    }

    @Override
    public Order getOrderById(Long id) {
        return orderRepository.findById(id).get();
    }
}
