package bg.rentacar.service.order;

import bg.rentacar.model.dto.OrderDTO;
import bg.rentacar.model.entity.Order;

public interface OrderService {
    void registerOrder(OrderDTO orderDTO, String username);

    Order getOrderById(Long id);
}
