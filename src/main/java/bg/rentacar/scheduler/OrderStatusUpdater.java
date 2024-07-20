package bg.rentacar.scheduler;

import bg.rentacar.service.OrderService;
import jakarta.transaction.Transactional;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class OrderStatusUpdater {
    private final OrderService orderService;

    public OrderStatusUpdater(OrderService orderService) {
        this.orderService = orderService;
    }

    @Scheduled(cron = "0 0 * * * *") // "0 0 * * * *" every hour || "0 */1 * * * *" every minute for testing
    @Transactional
    public void updateStatus(){
       orderService.finishOrder();
    }
}
