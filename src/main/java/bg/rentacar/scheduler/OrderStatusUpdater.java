package bg.rentacar.scheduler;

import bg.rentacar.model.entity.Car;
import bg.rentacar.model.entity.Order;
import bg.rentacar.model.enums.RentOrderStatus;
import bg.rentacar.repository.CarRepository;
import bg.rentacar.repository.OrderRepository;
import bg.rentacar.web.LoginController;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class OrderStatusUpdater {
    private final OrderRepository orderRepository;
    private final CarRepository carRepository;
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    public OrderStatusUpdater(OrderRepository orderRepository, CarRepository carRepository) {
        this.orderRepository = orderRepository;
        this.carRepository = carRepository;
    }

    @Scheduled(cron = "0 0 * * * *") //every hour
    @Transactional
    public void updateStatus(){
        List<Order> approvedOrders = orderRepository.findAllByStatus(RentOrderStatus.APPROVED);
        LocalDateTime now = LocalDateTime.now();
        for (Order order : approvedOrders) {
            LocalDateTime dropOffDateTime = LocalDateTime.of(order.getDropOffDate(), order.getDropOffTime());
            if(dropOffDateTime.isBefore(now)){
                order.setStatus(RentOrderStatus.APPROVED);
                orderRepository.save(order);
                Car car = order.getCar();
                car.setAvailable(true);
                carRepository.save(car);
                logger.info("Order with id: {} has been approved", order.getId());
            }
        }
        logger.info("{} orders have been updated", orderRepository.count());
    }
}
